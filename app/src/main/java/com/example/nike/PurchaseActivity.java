package com.example.nike;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PurchaseActivity extends AppCompatActivity {

    Button btn_end;
    TextView txt_total,txt_count;
    Intent inIntent;

    PurchaseListViewAdapter adapter;
    ListView purchaseItemList;
    ArrayList<ListViewItem> purchaseItems;

    int totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase_page);

        Log.i("check","PURCHASE_ONCREATE");

        btn_end = (Button) findViewById(R.id.purchase_btn_buy);
        txt_total = (TextView) findViewById(R.id.totalPrice);
        txt_count = (TextView) findViewById(R.id.totalCount);

        inIntent = getIntent();
        purchaseItems = inIntent.getParcelableArrayListExtra("purchaseItemList");

        totalPrice = 0;
        for(int i = 0;i<purchaseItems.size();i++){
            totalPrice += Integer.parseInt(purchaseItems.get(i).getItemPrice());
        }

        txt_total.setText("총 금액 : "+ totalPrice + "원");
        txt_count.setText("개수 : " + purchaseItems.size() + "개");

        adapter = new PurchaseListViewAdapter();

        // 리스트 뷰에 어댑터 설정
        purchaseItemList = (ListView) findViewById(R.id.list_purchaseItem);
        purchaseItemList.setAdapter(adapter);

        // purchaseItems에 있는 값들 purchaseListView에 출력
        for(int i = 0;i<purchaseItems.size();i++){
            String name = purchaseItems.get(i).getItemName();
            String price = purchaseItems.get(i).getItemPrice();
            adapter.addPurchaseItem(name,price);
        }

        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    // 구매 페이지로 이전으로 넘어가는 것을 방지
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("check","PURCHASE_ONRESTART");
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}
