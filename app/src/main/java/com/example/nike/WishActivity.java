package com.example.nike;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class WishActivity extends AppCompatActivity {

    Button btn_purchase, btn_home;
    Intent inIntent;

    WishListViewAdapter adapter;
    ListView wishItemList;

    ArrayList<ListViewItem> wishItems;
    ArrayList<ListViewItem> purchaseItems;
    ArrayList<Integer> selectedItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wish_page);

        Log.i("check","WISH_ONCREATE");

        btn_purchase = (Button) findViewById(R.id.wish_btn_buy);
        btn_home = (Button) findViewById(R.id.wish_btn_home);

        inIntent = getIntent();
        wishItems = inIntent.getParcelableArrayListExtra("selectedItemList");

        // 체크 표시된 아이템들만을 필터링 하기 위해 만든 List
        selectedItemPosition = new ArrayList<Integer>();
        purchaseItems = new ArrayList<ListViewItem>();

        adapter = new WishListViewAdapter();

        // 리스트 뷰에 어댑터 설정
        wishItemList = (ListView) findViewById(R.id.list_wishItem);
        wishItemList.setAdapter(adapter);

        // wishItems에 있는 값들 ListView에 출력
        for(int i = 0;i<wishItems.size();i++){
            String name = wishItems.get(i).getItemName();
            String price = wishItems.get(i).getItemPrice();
            adapter.addWishItem(name,price);
        }

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putParcelableArrayListExtra("savedWishItemList",wishItems);
                startActivity(intent);
            }
        });

        btn_purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PurchaseActivity.class);
                if(purchaseItems.size()!= 0){
                    intent.putParcelableArrayListExtra("purchaseItemList",purchaseItems);
                    startActivity(intent);
                }
            }
        });

        // 리스트 뷰에서 아이템 클릭 시
        wishItemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

                // 체크박스가 체크 되어있는 상태의 상품만 넘기도록 조건문 작성
                Boolean exist = false;
                if(selectedItemPosition.size() == 0){
                    selectedItemPosition.add(position);
                    purchaseItems.add(item);
                }else{
                    for(int i = 0;i<selectedItemPosition.size();i++){
                        if(position == selectedItemPosition.get(i)){
                            purchaseItems.remove(i);
                            selectedItemPosition.remove(i);
                            exist = true;
                            break;
                        }
                    }
                    if(exist == false){
                        selectedItemPosition.add(position);
                        purchaseItems.add(item);
                    }
                }
            }
        }) ;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.wish_page);

        Log.i("check","WISH_ONRESTART");

        btn_purchase = (Button) findViewById(R.id.wish_btn_buy);
        btn_home = (Button) findViewById(R.id.wish_btn_home);

        inIntent = getIntent();
        wishItems = inIntent.getParcelableArrayListExtra("selectedItemList");

        // 체크 표시된 아이템들만을 필터링 하기 위해 만든 List
        selectedItemPosition = new ArrayList<Integer>();
        purchaseItems = new ArrayList<ListViewItem>();

        adapter = new WishListViewAdapter();

        // 리스트 뷰에 어댑터 설정
        wishItemList = (ListView) findViewById(R.id.list_wishItem);
        wishItemList.setAdapter(adapter);

        // wishItems에 있는 값들 ListView에 출력
        for(int i = 0;i<wishItems.size();i++){
            String name = wishItems.get(i).getItemName();
            String price = wishItems.get(i).getItemPrice();
            adapter.addWishItem(name,price);
        }

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putParcelableArrayListExtra("savedWishItemList",wishItems);
                startActivity(intent);
            }
        });

        btn_purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PurchaseActivity.class);
                if(purchaseItems.size()!= 0){
                    intent.putParcelableArrayListExtra("purchaseItemList",purchaseItems);
                    startActivity(intent);
                }
            }
        });

        // 리스트 뷰에서 아이템 클릭 시
        wishItemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

                // 체크박스가 체크 되어있는 상태의 상품만 넘기도록 조건문 작성
                Boolean exist = false;
                if(selectedItemPosition.size() == 0){
                    selectedItemPosition.add(position);
                    purchaseItems.add(item);
                }else{
                    for(int i = 0;i<selectedItemPosition.size();i++){
                        if(position == selectedItemPosition.get(i)){
                            purchaseItems.remove(i);
                            selectedItemPosition.remove(i);
                            exist = true;
                            break;
                        }
                    }
                    if(exist == false){
                        selectedItemPosition.add(position);
                        purchaseItems.add(item);
                    }
                }
            }
        }) ;
    }
}
