package se.malmo.shoppinglist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    Context context;
    ArrayList<ListItem> items;

    public ItemAdapter(Context context, ArrayList<ListItem> items) {
        this.context = context;
        this.items = items;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // this method is called when a new view needs to be created

        // the Inflater takes a layout and convert it into a View
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.list_item, parent, false);

        // the ViewHolder is used to interact with the View
        ViewHolder holder = new ViewHolder(itemView);

        // lambda implementation of the event listener
        itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, EditListActivity.class);
            intent.putExtra("id", Integer.valueOf(holder.txtItem.getText().toString()));
            context.startActivity(intent);
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        ListItem item = items.get(position);

        holder.txtItem.setText(String.valueOf(item.getId()));

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    // how to interact with the view (layout)
    public class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView txtItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtItem = itemView.findViewById(R.id.txt_item);
        }
    }
}

