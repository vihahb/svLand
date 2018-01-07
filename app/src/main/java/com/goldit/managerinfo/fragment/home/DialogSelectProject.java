package com.goldit.managerinfo.fragment.home;


import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.goldit.managerinfo.R;
import com.goldit.managerinfo.coreapi.BaseRecyclerAdapter;
import com.goldit.managerinfo.fragment.model.Project;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.goldit.managerinfo.fragment.home.HomeFragment.homePresenter;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogSelectProject extends DialogFragment implements ItemClickListener {

    public static DialogSelectProject instance;
    RecyclerView recyclerViewListProject;

    public static DialogSelectProject getInstance() {
        instance = new DialogSelectProject();
        return instance;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_select_project, null);
        final Dialog dialog = new Dialog(getContext());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.setTitle("Finish");
        dialog.show();
        recyclerViewListProject = dialog.findViewById(R.id.recyclerListProjct);
        Bundle bundle = getArguments();
        if (bundle != null) {
            Project project = bundle.getParcelable("Project");
            if (project != null) {
                initListProject(dialog, project.getData());
            }
        }

        return dialog;
    }

    private void initListProject(Dialog dialog, List<Project.DataBean> data) {
        List<Project.DataBean> listProject = new ArrayList<>();
        Project.DataBean dataBean = new Project.DataBean();
        dataBean.setId(-1);
        dataBean.setProject_name("Tất cả dự án");
        listProject.add(dataBean);
        if (data != null) {
            listProject.addAll(data);
        }
        ProjectAdapter adapter = new ProjectAdapter();
        adapter.updateList(listProject);
        adapter.setClickListener(this);
        recyclerViewListProject.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewListProject.setAdapter(adapter);
    }

    @Override
    public void onClick(Project.DataBean item, int position) {
        homePresenter.setProjectUserSelected(item);
        dismiss();
    }


    class ProjectAdapter extends BaseRecyclerAdapter<Project.DataBean> {

        ItemClickListener clickListener;

        public void setClickListener(ItemClickListener clickListener) {
            this.clickListener = clickListener;
        }

        @Override
        protected int getLayoutItem() {
            return R.layout.item_list_project;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutItem(), parent, false);
            return new ProjectViewHolder(view);
        }


        class ProjectViewHolder extends ViewHolder {

            @BindView(R.id.nameProject)
            TextView nameProject;

            public ProjectViewHolder(View itemView) {
                super(itemView);
            }

            @Override
            public void populate(final Project.DataBean item, final int position) {
                if (item.getProject_name() != null) {
                    nameProject.setText(item.getProject_name());
                }
                nameProject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clickListener.onClick(item, position);
                    }
                });
            }
        }


    }


}
