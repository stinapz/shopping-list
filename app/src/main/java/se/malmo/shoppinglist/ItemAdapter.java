package se.malmo.shoppinglist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// klassen som skapar adaptern (adaptern är den som pratar med recyklerview och datan som man har)
// hur saker ska visas i en layout med hjälp av metoder
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    Context context;
    ArrayList<ListItem> items;
    private AdapterCallback adapterCallback;

    public ItemAdapter(Context context, ArrayList<ListItem> items) {
        this.context = context;
        this.items = items;
        try {
            adapterCallback = ((AdapterCallback) context);
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement AdapterCallback.");
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // denna metoden kallas när en ny veiw skapas

        //Inflatorn tar en layout och konverterar den till en view
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.list_item, parent, false);

        // Viewholdern används för att interagera med viewn
        ViewHolder holder = new ViewHolder(itemView);
        return holder;
    }

    // sätter vilka varor som ska visas och håller ihop informationen så den blir "flytande" när vi scrollar.
    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        ListItem item = items.get(position);

        holder.txtItem.setText(String.valueOf(item.getItem())); // visar texten
        holder.checkBox.setChecked(item.getIsChecked() == 1);  // denna bestämmer vad som ska visas i checked-boxen

        holder.txtItem.setOnClickListener(view -> {
            Intent intent = new Intent(context, EditListActivity.class);
            intent.putExtra("id", item.getId());
            context.startActivity(intent);
        });
        holder.checkBox.setOnClickListener(view -> {
            if (item.getIsChecked()==0)
                item.setIsChecked(1);
            else
                item.setIsChecked(0);

            try {
                adapterCallback.updateIsChecked(item);
            } catch (ClassCastException e) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    //viewholdern interagera med layout-viewn
    public class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView txtItem;
        public final CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtItem = itemView.findViewById(R.id.txt_item);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }

    public interface AdapterCallback {
        void updateIsChecked(ListItem item);
    }
}

