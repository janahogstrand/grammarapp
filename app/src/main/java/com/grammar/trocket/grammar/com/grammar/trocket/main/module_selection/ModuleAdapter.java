package com.grammar.trocket.grammar.com.grammar.trocket.main.module_selection;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.main.MainMenu;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.recyclerview.FestivalTimeViewHolder;

import java.util.ArrayList;

/**
 * Created by jamiemoreland on 18/03/16.
 */
public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ModuleViewHolder> {

    private  ArrayList<ModuleItem> moduleData;

    public class ModuleViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        private View view;
        ArrayList<ModuleItem> moduleItems;

        public ModuleViewHolder(View itemView, final ArrayList<ModuleItem> moduleData) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            this.moduleItems = moduleData;

            view = itemView;

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences prefs = v.getContext().getSharedPreferences(
                            "com.grammar.trocket.grammar.com.grammar.trocket.main", v.getContext().MODE_PRIVATE);

                    prefs.edit().putString(ModuleSelection.LANGUAGE, title.getText().toString()).apply();
                    int id = moduleItems.get(getAdapterPosition()).getModuleId();

                    prefs.edit().putInt("courseId", id).apply();
                    String savedID = Integer.toString(prefs.getInt("courseId", 0));

                    MainMenu.MainLanguage = prefs.getString(ModuleSelection.LANGUAGE, new String());
                    Log.w("Language is: ", MainMenu.MainLanguage);
                    Log.w("ID is: ", savedID);

                    Intent intent = new Intent(view.getContext(), MainMenu.class);
                    view.getContext().startActivity(intent);
                }
            });


        }
    }

    public ModuleAdapter(ArrayList<ModuleItem> moduleData){
        this.moduleData = moduleData;
    }

    @Override
    public ModuleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_festival, parent, false);
        ModuleViewHolder viewHolder = new ModuleViewHolder(v, moduleData);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ModuleViewHolder holder, int position) {
        TextView title = holder.title;
        title.setText(moduleData.get(position).getModuleName());
    }

    @Override
    public int getItemCount() {
        return moduleData.size();
    }

}
