package com.leaflet.gameapp.presentation.ui.util;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.os.Handler;
import android.widget.ImageView;

import com.leaflet.gameapp.R;
import com.leaflet.gameapp.domain.communicator.Clickable;
import com.leaflet.gameapp.domain.communicator.OnScoreChange;
import com.leaflet.gameapp.domain.models.Level;
import com.leaflet.gameapp.domain.models.Node;

/**
 * Created by yasser.ibrahim on 5/8/2018.
 */

public class ItemsClickedManager implements Clickable {
    private Context context;
    private Node[][] nodes;
    private Node previousNode;
    private Level level;
    private final Handler handler;
    private final int defaultResource;
    private OnScoreChange onScoreChange;

    public ItemsClickedManager(Context context, Level level, OnScoreChange onScoreChange) {
        this.context = context;
        setLevel(level);
        this.previousNode = new Node();
        this.defaultResource = R.drawable.ic_question_mark;
        this.handler = new Handler();
        this.onScoreChange = onScoreChange;
    }

    public final void setLevel(Level level) {
        this.level = level;
        this.nodes = Initializer.generate(level.getRows(), level.getColumns());
    }

    public Level getLevel() {
        return level;
    }

    @Override
    public void onClick(ImageView image, int row, int column) {
        final Node node = nodes[row - 1][column - 1];
        node.setImage(image);

        if (!node.isVisible()) {
            changeView(node, false);

            if (previousNode.isCleared()) {
                previousNode.fill(node);
                previousNode.setImage(image);
            } else if (previousNode.equals(node)) {
                onScoreChange.onScoreChange();
                previousNode.clear();
            } else {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        changeView(nodes[previousNode.getRow()][previousNode.getColumn()], true);
                        changeView(node, true);
                        previousNode.clear();
                    }
                }, 1000);
            }
        }
    }

    private void changeView(Node node, boolean isCleared) {
        node.getImage().setImageResource(isCleared ? defaultResource : node.getResource());
        animateImage(node.getImage(), true);
        node.setVisible(!isCleared);
    }

    private void animateImage(ImageView image, boolean isSelected) {
        AnimatorSet animator;
        if (isSelected) {
            animator = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.in_animator);
        } else {
            animator = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.out_animator);
        }
        animator.setTarget(image);
        animator.start();
    }
}