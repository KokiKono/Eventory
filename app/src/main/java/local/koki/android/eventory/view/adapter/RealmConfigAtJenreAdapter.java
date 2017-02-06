package local.koki.android.eventory.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import local.koki.android.eventory.R;
import local.koki.android.eventory.model.JenreRealm;
import local.koki.android.eventory.view.controller.ConfigAtJenreAcitivty;
import local.koki.android.eventory.view.util.Colors;

/**
 * Created by 浩生 on 2017/01/28.
 */

public class RealmConfigAtJenreAdapter extends RealmRecyclerViewAdapter<JenreRealm,RealmConfigAtJenreAdapter.ViewHolder>{
    private ConfigAtJenreAcitivty activity;

    public RealmConfigAtJenreAdapter(ConfigAtJenreAcitivty activity, OrderedRealmCollection<JenreRealm> datas) {
        super(activity.getApplicationContext(), datas, true);
        this.activity = activity;
    }

    @Override
    public RealmConfigAtJenreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.config_item, parent, false);
        return new RealmConfigAtJenreAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RealmConfigAtJenreAdapter.ViewHolder holder, int position) {
        JenreRealm realm = getData().get(position);
        holder.checkedTextView.setChecked(realm.getStatus());
        holder.checkedTextView.setText(realm.getName());
        holder.checkedTextView.setTextColor(Colors.Companion.isColor(realm.getStatus()));
        holder.data = getData().get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public JenreRealm data;
        public CheckedTextView checkedTextView;

        public ViewHolder(View v) {
            super(v);
            checkedTextView=(CheckedTextView)v.findViewById(R.id.check_text);
            checkedTextView.setOnClickListener(this);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            checkedTextView.toggle();
            checkedTextView.setTextColor(Colors.Companion.isColor(checkedTextView.isChecked()));
            activity.changeItem(data);
        }
    }
}
