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
        // denna metod blir kallad och körs när en ny view behöver skapas

        // Inflatern tar en layout och konverterar den tille n view
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.list_item, parent, false);

        // ViewHolder behövs för att interagerar med view'n
        ViewHolder holder = new ViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        ListItem item = items.get(position);

        holder.txtItem.setText(String.valueOf(item.getItem()));
        holder.txtItem.setOnClickListener(view -> {
            Intent intent = new Intent(context, EditListActivity.class);
            intent.putExtra("id", item.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    // hur man interagerar med view-layouten
    public class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView txtItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtItem = itemView.findViewById(R.id.txt_item);
        }
    }
}

