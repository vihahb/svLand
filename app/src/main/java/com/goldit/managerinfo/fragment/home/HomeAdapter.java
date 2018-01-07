package com.goldit.managerinfo.fragment.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.goldit.managerinfo.R;
import com.goldit.managerinfo.coreapi.BaseRecyclerAdapter;
import com.goldit.managerinfo.fragment.model.Contact;

import butterknife.BindView;

/**
 * Created by baonguyen on 12/26/17.
 */

public class HomeAdapter extends BaseRecyclerAdapter<Contact.User> {

    ItemClickListener clickListener;
    @Override
    protected int getLayoutItem() {
        return R.layout.item_list_contact;
    }

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutItem(), parent, false);
        return new ListContactViewHolder(view);
    }

    class ListContactViewHolder extends ViewHolder {
        @BindView(R.id.imageContact)
        TextView imageContact;
        @BindView(R.id.nameUserContact)
        TextView nameUserContact;
        @BindView(R.id.telUserContact)
        TextView telUserContact;

        @BindView(R.id.actionToDetailContact)
        LinearLayout actionToDetailContact;

        public ListContactViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void populate(final Contact.User item, final int position) {
                if (item.getName().equals(""))
                    nameUserContact.setText(item.getMsisdn());
                else {
                    imageContact.setText(item.getName().substring(0, 1).toUpperCase());
                    nameUserContact.setText(item.getName());
                }
                telUserContact.setText(item.getMsisdn());
                switch (item.getStatus()) {
                    case "0":
                        //0 - Chưa Tele
                        imageContact.setText("CT");
                        imageContact.setBackgroundResource(R.drawable.bg_imagecircle_gray);
                        break;
                    case "1":
                        //1 - Đồng ý gặp Sale/Đang theo
                        imageContact.setBackgroundResource(R.drawable.bg_imagecircle_green);

                        break;
                    case "2":
                        //2 - Đã từ chối
                        imageContact.setBackgroundResource(R.drawable.bg_imagecircle_red);

                        break;
                    case "3":
                        //3 - Không nghe máy
                        imageContact.setText("CN");
                        imageContact.setBackgroundResource(R.drawable.bg_imagecircle_yellow);

                        break;
                    case "4":
                        //4 - Đã khóa
                        imageContact.setBackgroundResource(R.drawable.bg_imagecircle_red);
                        break;
                    case "5":
                        //5 - Da chốt
                        imageContact.setBackgroundResource(R.drawable.bg_imagecircle_ogrange);
                        break;
                    case "6":
                        //6 - Quan tam - Goi lai
                        imageContact.setText("QT");
                        imageContact.setBackgroundResource(R.drawable.bg_imagecircle_pink);
                        break;
                }

                actionToDetailContact.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clickListener.onClick(item, position);
                    }
                });
            }


    }


    interface ItemClickListener {
        void onClick(Contact.User contact, int position);
    }
}

