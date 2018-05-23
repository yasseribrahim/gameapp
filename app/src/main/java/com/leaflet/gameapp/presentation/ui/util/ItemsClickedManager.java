package com.leaflet.gameapp.presentation.ui.util;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.os.Handler;
import android.widget.ImageView;

import com.leaflet.gameapp.R;
import com.leaflet.gameapp.domain.communicator.Clickable;
import com.leaflet.gameapp.domain.communicator.ImageViewHandler;
import com.leaflet.gameapp.domain.communicator.OnScoreChanged;
import com.leaflet.gameapp.domain.models.Level;
import com.leaflet.gameapp.domain.models.Node;
import com.leaflet.gameapp.domain.utils.ConstantsValues;

import java.util.Random;

/**
 * Created by yasser.ibrahim on 5/8/2018.
 */

public class ItemsClickedManager implements Clickable {
    private Context context;
    private Node[][] nodes;
    private Node previousNode;
    private Level level;
    private final Handler handler;
    private Runnable runnable;
    private final int defaultResource;
    private OnScoreChanged onScoreChanged;
    private ImageViewHandler imageHandler;
    private boolean isClicksAvailable;
    private boolean isColumnSelected;

    public ItemsClickedManager(Context context, Level level, OnScoreChanged onScoreChanged, ImageViewHandler imageHandler) {
        this.context = context;
        this.previousNode = new Node();
        this.defaultResource = R.drawable.ic_question_mark;
        this.handler = new Handler();
        this.onScoreChanged = onScoreChanged;
        this.imageHandler = imageHandler;
        setLevel(level);
        isClicksAvailable = true;
        isColumnSelected = false;
    }

    public final void setLevel(Level level) {
        this.level = level;
        this.nodes = Initializer.getCurrentInitializer().generate(level, imageHandler);
    }

    public Level getLevel() {
        return level;
    }

    public void onDestroy() {
        if (runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }

    @Override
    public void onClick(int row, int column) {
        final Node node = nodes[row - 1][column - 1];

        if (!node.isVisible() && isClicksAvailable) {
            changeView(node, false);

            if (previousNode.isCleared()) {
                previousNode.fill(node);
                isClicksAvailable = true;
            } else if (previousNode.equals(node)) {
                onScoreChanged.onScoreChange();
                previousNode.clear();
                isClicksAvailable = true;
            } else {
                isClicksAvailable = false;
                onDestroy();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        changeView(nodes[previousNode.getRow()][previousNode.getColumn()], true);
                        changeView(node, true);
                        previousNode.clear();
                        isClicksAvailable = true;
                    }
                };
                handler.postDelayed(runnable, 1000);
            }
        }
    }

    public void showHint() {
        Node[] nodes = selectRandomNodes();
        for (int i = 0; i < nodes.length; i++) {
            final Node node = nodes[i];
            if (!nodes[i].isVisible()) {
                changeView(node, false);
                handler.postDelayed(new Runnable() {
                    Node currentNode = node;

                    @Override
                    public void run() {
                        changeView(currentNode, true);
                    }
                }, ConstantsValues.HINT_TIME_DISPLAY);
            }
        }
    }

    private Node[] selectRandomNodes() {
        Node[] nodes = null;
        Random random = new Random();
        int index;
        if (isColumnSelected) {
            index = random.nextInt(level.getColumns());
            nodes = new Node[level.getRows()];
            for (int row = 0; row < nodes.length; row++) {
                nodes[row] = this.nodes[row][index];
            }
        } else {
            index = random.nextInt(level.getRows());
            nodes = this.nodes[index];
        }
        isColumnSelected = !isColumnSelected;
        return nodes;
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
