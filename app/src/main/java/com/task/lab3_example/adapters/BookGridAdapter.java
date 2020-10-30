package com.task.lab3_example.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.task.lab3_example.R;
import com.task.lab3_example.data.Book;

import java.util.List;

public class BookGridAdapter extends BaseAdapter {
    private final Context context;
    private final List<Book>books;
    private final LayoutInflater layoutInflater;

    public BookGridAdapter(Context context, List<Book> books){
        this.context = context;
        this.books = books;
        layoutInflater = LayoutInflater.from(context);
    }

    public void addBook(Book book){
        books.add(book);
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int i) {
        return books.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.grid_item, null);
            holder = new ViewHolder();
            holder.titleView = convertView.findViewById(R.id.tvBookTitle);
            holder.authorView = convertView.findViewById(R.id.tvBookAuthor);
            holder.layout = convertView.findViewById(R.id.item_back_layout);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Book book = books.get(position);
        if(book == null) {
            holder.layout.setVisibility(View.INVISIBLE);
            return convertView;
        }
        holder.titleView.setText(book.getTitle());
        holder.authorView.setText(book.getAuthor());

       // int imageId = this.getMipmapResIdByName(country.getFlagName());

      //  holder.flagView.setImageResource(imageId);

        return convertView;
    }

    // Find Image ID corresponding to the name of the image (in the directory mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();

        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomGridView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    static class ViewHolder {
        LinearLayout layout;
        TextView titleView;
        TextView authorView;
    }
}
