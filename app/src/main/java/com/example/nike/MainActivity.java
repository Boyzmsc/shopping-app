package com.example.nike;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MainListViewAdapter adapter;
    ListView itemList;
    Button btn_purchase, btn_wish;

    Intent inIntent;

    ArrayList<ListViewItem> selectedItems;
    ArrayList<ListViewItem> savedWishItems;
    ArrayList<Integer> selectedItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("check","MAIN_ONCREATE");

        btn_purchase = (Button) findViewById(R.id.main_btn_buy);
        btn_wish = (Button) findViewById(R.id.main_btn_wish);

        // 선택된 아이템들을 담는 List
        selectedItems = new ArrayList<ListViewItem>();

        // 체크 표시된 아이템들만을 필터링 하기 위해 만든 List
        selectedItemPosition = new ArrayList<Integer>();

        // 장바구니에 저장되어 있는 아이템들 받아오기 위한 List
        savedWishItems = new ArrayList<ListViewItem>();

        inIntent = getIntent();
        savedWishItems = inIntent.getParcelableArrayListExtra("savedWishItemList");

        // 장바구니 버튼 클릭 시
        btn_wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),WishActivity.class);

                // 아무것도 선택 안할 시 다음 액티비티로 넘어가지 않음
                if(selectedItems.size()!= 0){
                    // 장바구니에서 넘어온 데이터가 없을 시 그냥 넘기기 위한 조건문
                    if (savedWishItems != null) {
                        selectedItems.addAll(savedWishItems);
                    }
                    intent.putParcelableArrayListExtra("selectedItemList",selectedItems);
                    startActivity(intent);
                }
            }
        });

        // 구매 버튼 클릭 시
        btn_purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PurchaseActivity.class);

                // 아무것도 선택 안할 시 다음 액티비티로 넘어가지 않음
                if(selectedItems.size()!= 0){
                    intent.putParcelableArrayListExtra("purchaseItemList",selectedItems);
                    startActivity(intent);
                }
            }
        });

        adapter = new MainListViewAdapter();

        itemList = (ListView) findViewById(R.id.list_item);
        itemList.setAdapter(adapter);

        // ListView에 상품들 추가
        adapter.addMainItem(ContextCompat.getDrawable(this, R.drawable.mtm),
                "Nike Club MTM (Black)", "50000");
        adapter.addMainItem(ContextCompat.getDrawable(this, R.drawable.cap),
                "Nike Casual Cap (Black)", "30000");
        adapter.addMainItem(ContextCompat.getDrawable(this, R.drawable.pants),
                "Nike Training Pants (Black)", "20000");
        adapter.addMainItem(ContextCompat.getDrawable(this, R.drawable.shoes),
                "Nike Air Force 1'07 (Black)", "80000");
        adapter.addMainItem(ContextCompat.getDrawable(this, R.drawable.tshirt),
                "Nike Design T-Shirt (Black)", "10000");
        adapter.addMainItem(ContextCompat.getDrawable(this, R.drawable.shoes_o),
                "Nike Air Force LV8 3 (Orange)", "200000");
        adapter.addMainItem(ContextCompat.getDrawable(this, R.drawable.shoes_w),
                "Nike Air Force 1 (White)", "150000");
        adapter.addMainItem(ContextCompat.getDrawable(this, R.drawable.shoes_r),
                "Nike Air Force GB (Red&Black)", "130000");
        adapter.addMainItem(ContextCompat.getDrawable(this, R.drawable.shoes_c),
                "Nike Air Force VN (Nintendo)", "400000");

        // 리스트 뷰에서 아이템 클릭 시
        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

                // 체크박스가 체크 되어있는 상태의 상품만 넘기도록 조건문 작성
                Boolean exist = false;
                if(selectedItemPosition.size() == 0){
                    selectedItemPosition.add(position);
                    selectedItems.add(item);
                }else{
                    for(int i = 0;i<selectedItemPosition.size();i++){
                        if(position == selectedItemPosition.get(i)){
                            selectedItems.remove(i);
                            selectedItemPosition.remove(i);
                            exist = true;
                            break;
                        }
                    }
                    if(exist == false){
                        selectedItemPosition.add(position);
                        selectedItems.add(item);
                    }
                }
            }
        }) ;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_main);

        Log.i("check","MAIN_ONRESTART");

        btn_purchase = (Button) findViewById(R.id.main_btn_buy);
        btn_wish = (Button) findViewById(R.id.main_btn_wish);

        // 선택된 아이템들을 담는 List
        selectedItems = new ArrayList<ListViewItem>();

        // 체크 표시된 아이템들만을 필터링 하기 위해 만든 List
        selectedItemPosition = new ArrayList<Integer>();

        // 장바구니에 저장되어 있는 아이템들 받아오기 위한 List
        savedWishItems = new ArrayList<ListViewItem>();

        inIntent = getIntent();
        savedWishItems = inIntent.getParcelableArrayListExtra("savedWishItemList");

        // 장바구니 버튼 클릭 시
        btn_wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),WishActivity.class);

                // 아무것도 선택 안할 시 다음 액티비티로 넘어가지 않음
                if(selectedItems.size()!= 0){
                    // 장바구니에서 넘어온 데이터가 없을 시 그냥 넘기기 위한 조건문
                    if (savedWishItems != null) {
                        selectedItems.addAll(savedWishItems);
                    }
                    intent.putParcelableArrayListExtra("selectedItemList",selectedItems);
                    startActivity(intent);
                }
            }
        });

        // 구매 버튼 클릭 시
        btn_purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PurchaseActivity.class);

                // 아무것도 선택 안할 시 다음 액티비티로 넘어가지 않음
                if(selectedItems.size()!= 0){
                    intent.putParcelableArrayListExtra("purchaseItemList",selectedItems);
                    startActivity(intent);
                }
            }
        });

        adapter = new MainListViewAdapter();

        itemList = (ListView) findViewById(R.id.list_item);
        itemList.setAdapter(adapter);

        // ListView에 상품들 추가
        adapter.addMainItem(ContextCompat.getDrawable(this, R.drawable.mtm),
                "Nike Club MTM (Black)", "50000");
        adapter.addMainItem(ContextCompat.getDrawable(this, R.drawable.cap),
                "Nike Casual Cap (Black)", "30000");
        adapter.addMainItem(ContextCompat.getDrawable(this, R.drawable.pants),
                "Nike Training Pants (Black)", "20000");
        adapter.addMainItem(ContextCompat.getDrawable(this, R.drawable.shoes),
                "Nike Air Force 1'07 (Black)", "80000");
        adapter.addMainItem(ContextCompat.getDrawable(this, R.drawable.tshirt),
                "Nike Design T-Shirt (Black)", "10000");
        adapter.addMainItem(ContextCompat.getDrawable(this, R.drawable.shoes_o),
                "Nike Air Force LV8 3 (Orange)", "200000");
        adapter.addMainItem(ContextCompat.getDrawable(this, R.drawable.shoes_w),
                "Nike Air Force 1 (White)", "150000");
        adapter.addMainItem(ContextCompat.getDrawable(this, R.drawable.shoes_r),
                "Nike Air Force GB (Red&Black)", "130000");
        adapter.addMainItem(ContextCompat.getDrawable(this, R.drawable.shoes_c),
                "Nike Air Force VN (Nintendo)", "400000");

        // 리스트 뷰에서 아이템 클릭 시
        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

                // 체크박스가 체크 되어있는 상태의 상품만 넘기도록 조건문 작성
                Boolean exist = false;
                if(selectedItemPosition.size() == 0){
                    selectedItemPosition.add(position);
                    selectedItems.add(item);
                }else{
                    for(int i = 0;i<selectedItemPosition.size();i++){
                        if(position == selectedItemPosition.get(i)){
                            selectedItems.remove(i);
                            selectedItemPosition.remove(i);
                            exist = true;
                            break;
                        }
                    }
                    if(exist == false){
                        selectedItemPosition.add(position);
                        selectedItems.add(item);
                    }
                }
            }
        }) ;
    }
}