package com.example.book_app.filters;
import android.widget.Filter;

import com.example.book_app.adapters.AdapterCategory;
import com.example.book_app.models.ModelCategory;

import java.util.ArrayList;

public class FilterCategory extends Filter {
    //Arraylist in which we want to search
    ArrayList<ModelCategory> filterList;

    //adapter in which filter need to be implemented
    AdapterCategory adapterCategory;

    //constructor
    public FilterCategory(ArrayList<ModelCategory> filterList, AdapterCategory adapterCategory) {
        this.filterList = filterList;
        this.adapterCategory = adapterCategory;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        //value should not be null or empty
        if(constraint != null && constraint.length() > 0){
            //change to uppercase / lowercase to avoid case sensitivity
            constraint = constraint.toString().toUpperCase();
            ArrayList<ModelCategory> filterModels = new ArrayList<>();

            for (int i=0; i<filterList.size(); i++){
                //validate
                if(filterList.get(i).getCategory().toUpperCase().contains(constraint)){
                    //add to filtered list
                    filterModels.add(filterList.get(i));
                }
            }
            results.count = filterModels.size();
            results.values = filterModels;
        }
        else{
            results.count = filterList.size();
            results.values = filterList;
        }
        return results; // dont miss it
    }

    @Override
    protected void publishResults(CharSequence constraints, FilterResults filterResults) {
        // Apply filter changes
        adapterCategory.categoryArrayList = (ArrayList<ModelCategory>)filterResults.values;

        //notify change
        adapterCategory.notifyDataSetChanged();

    }
}
