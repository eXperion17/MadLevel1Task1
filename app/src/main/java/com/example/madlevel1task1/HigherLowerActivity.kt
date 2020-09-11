package com.example.madlevel1task1

import android.graphics.drawable.Drawable
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.madlevel1task1.databinding.ActivityHigherLowerBinding

class HigherLowerActivity : AppCompatActivity() {
    private var currentThrow: Int = 1;
    private var lastThrow: Int = 1;

    /** EARNED: No need to declare each reference to the objects in the layout, gave a pretty
     * nasty error that got me stuck for a little while.
     * */
    //private var imageDice: ImageView = binding.imageDice;

    private lateinit var binding:ActivityHigherLowerBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_higher_lower);
        binding = ActivityHigherLowerBinding.inflate(layoutInflater);
        setContentView(binding.root);

        initViews();
    }

    private fun initViews() {
        binding.textInfo.text = getString(R.string.game_info);

        binding.buttonHigher.setOnClickListener {
            rollDice();
            onHigherClick();
        }
        binding.buttonEqual.setOnClickListener {
            rollDice();
            onEqualClick();
        }
        binding.buttonLower.setOnClickListener {
            rollDice();
            onLowerClick();
        }

        updateUI();
    }

    /** Update last throw text + dice image with current throw */
    private fun updateUI() {
        binding.textLastthrow.text = getString(R.string.last_throw, lastThrow);

        /** LEARNED: Couldn't quite figure out how to use a temporary variable to avoid redundancy
         * in the 'switch statement' equivalent of Kotlin. My goal was to use the variable dice
         * to hold the drawable and assign it after the statement.
         * */
        //var dice:Drawable;

        when (currentThrow) {
            1 -> binding.imageDice.setImageDrawable(getDrawable(R.drawable.dice1));
            2 -> binding.imageDice.setImageDrawable(getDrawable(R.drawable.dice2));
            3 -> binding.imageDice.setImageDrawable(getDrawable(R.drawable.dice3));
            4 -> binding.imageDice.setImageDrawable(getDrawable(R.drawable.dice4));
            5 -> binding.imageDice.setImageDrawable(getDrawable(R.drawable.dice5));
            6 -> binding.imageDice.setImageDrawable(getDrawable(R.drawable.dice6));
        }
    }

    private fun rollDice() {
        lastThrow = currentThrow;
        currentThrow = (1..6).random();
        updateUI();
    }

    private fun onHigherClick() {
        if (currentThrow > lastThrow)
            onAnswerCorrect();
        else
            onAnswerIncorrect();
    }

    private fun onLowerClick() {
        if (currentThrow < lastThrow)
            onAnswerCorrect();
        else
            onAnswerIncorrect();
    }

    private fun onEqualClick() {
        if (currentThrow == lastThrow)
            onAnswerCorrect();
        else
            onAnswerIncorrect();
    }

    private fun onAnswerCorrect() {
        binding.textResult.text = getString(R.string.result_won);
        Toast.makeText(this, getString(R.string.result_won), Toast.LENGTH_LONG).show();
    }

    private fun onAnswerIncorrect() {
        binding.textResult.text = getString(R.string.result_lost);
        Toast.makeText(this, getString(R.string.result_lost), Toast.LENGTH_LONG).show();
    }

}