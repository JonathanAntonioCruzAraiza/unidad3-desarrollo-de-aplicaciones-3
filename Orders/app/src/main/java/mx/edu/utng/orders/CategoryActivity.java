package mx.edu.utng.orders;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mx.edu.utng.orders.adapters.CategoryAdapter;
import mx.edu.utng.orders.model.Category;
import mx.edu.utng.orders.sqlite.DBOperations;

public class CategoryActivity extends AppCompatActivity {

    private EditText etCategoryName;
    private EditText etListPosition;

    private Button btSaveCategory;
    private DBOperations operations;
    private Category category = new Category();
    private RecyclerView rvCategories;
    private List<Category> categories =new ArrayList<Category>();
    private CategoryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        operations=DBOperations.getDBOperations(getApplicationContext());
        category.setIdCategory("");
        initComponents();
    }
    private void initComponents(){
        rvCategories =(RecyclerView)findViewById(R.id.rv_category_list);
        rvCategories.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        rvCategories.setLayoutManager(layoutManager);
        getCategories();
        adapter=new CategoryAdapter(categories);
        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.bt_delete_category:
                        operations.deleteCategory(categories.get(
                                rvCategories.getChildPosition((View)v.getParent().getParent())).getIdCategory());
                        getCategories();
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.bt_edit_category:
                        Toast.makeText(getApplicationContext(),"Editar",Toast.LENGTH_SHORT).show();
                        Cursor c = operations.getCategoryById(categories.get(
                                rvCategories.getChildPosition(
                                        (View)v.getParent().getParent())).getIdCategory());
                        if (c!=null){
                            if (c.moveToFirst()){
                                category = new Category(c.getString(1),c.getString(2),c.getString(3));
                            }
                            etCategoryName.setText(category.getCategoryName());
                            etListPosition.setText(category.getListPosition());

                        }else{
                            Toast.makeText(getApplicationContext(),"Registro no encontrado",Toast.LENGTH_SHORT).show();
                        }
                        break;
                }

            }
        });
        rvCategories.setAdapter(adapter);

        etCategoryName =(EditText)findViewById(R.id.et_category_name);
        etListPosition =(EditText)findViewById(R.id.et_list_position);
        btSaveCategory =(Button)findViewById(R.id.bt_save_category);

        btSaveCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!category.getIdCategory().equals("")){
                    category.setCategoryName(etCategoryName.getText().toString());
                    category.setListPosition(etListPosition.getText().toString());
                    operations.updateCategory(category);

                }else {
                    category = new Category("", etCategoryName.getText().toString(), etListPosition.getText().toString());
                    operations.insertCategory(category);
                }
                getCategories();
                clearData();
                adapter.notifyDataSetChanged();
            }
        });

    }
    private void getCategories(){
        Cursor c =operations.getCategories();
        categories.clear();
        if(c!=null){
            while (c.moveToNext()){
                categories.add(new Category(c.getString(1),c.getString(2),c.getString(3)));
            }
        }

    }

    private void clearData(){
        etCategoryName.setText("");
        etListPosition.setText("");
        category =null;
        category = new Category();
        category.setIdCategory("");
    }
}
