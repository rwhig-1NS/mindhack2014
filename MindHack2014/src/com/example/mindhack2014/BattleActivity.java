package com.example.mindhack2014;

import com.example.mindhack2014.Muffin;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class BattleActivity extends ActionBarActivity {

	private int muffHP = 20, muffDmg = 5, aiMuffHP = 25, aiMuffDmg = 5;
	private String muffURL = "", aiMuffURL = "";
	
	Muffin p1Muffin = new Muffin(muffHP, muffDmg, muffURL);
	Muffin aiMuffin = new Muffin(aiMuffHP, aiMuffDmg, aiMuffURL);	
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battle);
		TextView p1TextView = (TextView)findViewById(R.id.textViewP1HP);
		TextView aiTextView = (TextView)findViewById(R.id.textViewAIHP);		
		p1TextView.setText(p1Muffin.getMuffinHP() + " / " + p1Muffin.getMuffinMaxHP());
		aiTextView.setText(aiMuffin.getMuffinHP() + " / " + aiMuffin.getMuffinMaxHP());
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.battle, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void dealDmg(View view) {
			TextView aiTextView = (TextView)findViewById(R.id.textViewAIHP);
			if (aiMuffin.getMuffinHP() > 0)
				aiMuffin.setMuffinHP(-5);
			else
			{
				TextView msg = (TextView)findViewById(R.id.textViewEndBattle);
				msg.setText("YOU WIN!!!");
			}

			aiTextView.setText(aiMuffin.getMuffinHP() + " / " + aiMuffin.getMuffinMaxHP());
	   }
}
