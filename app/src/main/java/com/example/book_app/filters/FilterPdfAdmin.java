package com.example.book_app.filters;
import android.widget.Filter;

import com.example.book_app.adapters.AdapterCategory;
import com.example.book_app.adapters.AdapterPdfAdmin;
import com.example.book_app.models.ModelCategory;
import com.example.book_app.models.ModelPdf;

import java.util.ArrayList;

public class FilterPdfAdmin extends Filter {
    //Arraylist in which we want to search
    ArrayList<ModelPdf> filterList;

    //adapter in which filter need to be implemented
    AdapterPdfAdmin adapterPdfAdmin;

    //constructor
    public FilterPdfAdmin(ArrayList<ModelPdf> filterList, AdapterPdfAdmin adapterPdfAdmin) {
        this.filterList = filterList;
        this.adapterPdfAdmin = adapterPdfAdmin;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        //value should not be null or empty
        if(constraint != null && constraint.length() > 0){
            //change to uppercase / lowercase to avoid case sensitivity
            constraint = constraint.toString().toUpperCase();
            ArrayList<ModelPdf> filterModels = new ArrayList<>();

            for (int i=0; i<filterList.size(); i++){
                //validate
                if(filterList.get(i).getTitle().toUpperCase().contains(constraint)){
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
        adapterPdfAdmin.pdfArrayList = (ArrayList<ModelPdf>)filterResults.values;

        //notify change
        adapterPdfAdmin.notifyDataSetChanged();

    }
}
