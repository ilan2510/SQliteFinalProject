package com.example.ailan.sqlitefinalproject;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    MessageOpenHelper coh;
    AccountOpenHelper aoh;

    ArrayList<Message> listOfMessage;
    ArrayList<Account> listOfAccount;

    ListView lv , lv2;

    MessageAdapter messageAdapter;
    AccountAdapter accountAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listOfMessage=new ArrayList<Message>();
        listOfAccount=new ArrayList<Account>();

        coh=new MessageOpenHelper(this);
        aoh=new AccountOpenHelper(this);

        lv=(ListView)findViewById(R.id.lv);
        lv2=(ListView)findViewById(R.id.lv2);

        Log.d("Account", "list size is " + listOfAccount.size());
        Log.d("Account", AccountOpenHelper.CREATE_TABLE_ACCOUNT);

        Log.d("Message", "list size is " + listOfMessage.size());
        Log.d("Message", MessageOpenHelper.CREATE_TABLE_MESSAGE);

        coh.open();
        listOfMessage=coh.getAllmessages();
        coh.close();

        if(listOfMessage.size()==0)
        {
            createMessages();
        }
        createAccounts();
        messageAdapter=new MessageAdapter(this,0,listOfMessage);
        lv.setAdapter(messageAdapter);


        aoh.open();
        listOfAccount=aoh.getAllAccounts();
        aoh.close();

        if (listOfAccount.size()==0)
        {
            createAccounts();
        }
        accountAdapter=new AccountAdapter(this, 0,listOfAccount);
        lv2.setAdapter(accountAdapter);


    }


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    public void createMessages()
    {
        coh.open();
        Message c1=new Message(0,"12/10/2018","19:43","howareyoubro ?","shaked");
        c1=coh.createMessage(c1);
        listOfMessage.add(c1);

        Message c2=new Message(0,"22/11/2017","14:25","whereareyou ?","eli");
        c2=coh.createMessage(c2);
        listOfMessage.add(c2);
        coh.close();
        Log.d("Message", "list size is " + listOfMessage.size());
    }

    public void createAccounts()
    {
        aoh.open();
        Account c1=new Account(0,"ilan" , "moshayev", "amira2510", "ilan132465@walla.com", "Ujhsk25a4f");
        c1=aoh.createAccount(c1);
        listOfAccount.add(c1);

        Account c2=new Account(0, "eli", "kozinets" , "elilike2eat" , "elikozinets@gmail.com" , "Ujmdda25w9");
        c2=aoh.createAccount(c2);
        listOfAccount.add(c2);
        aoh.close();

        Log.d("Account" , "list size is :" + listOfAccount.size());


    }
}
