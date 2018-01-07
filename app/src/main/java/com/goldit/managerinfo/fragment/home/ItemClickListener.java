package com.goldit.managerinfo.fragment.home;

import com.goldit.managerinfo.fragment.model.Project;

/**
 * Created by baonguyen on 12/29/17.
 */

public interface ItemClickListener {
    void onClick(Project.DataBean item, int position);
}
