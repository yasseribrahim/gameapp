package com.leaflet.gameapp.presentation.ui.util;

import com.leaflet.gameapp.R;
import com.leaflet.gameapp.domain.communicator.ResourceHandler;
import com.leaflet.gameapp.domain.models.Level;
import com.leaflet.gameapp.domain.models.Node;
import com.leaflet.gameapp.domain.utils.GeneratorManager;

/**
 * Created by yasser.ibrahim on 5/8/2018.
 */

public class Initializer implements ResourceHandler {
    private static final Initializer INITIALIZER = new Initializer();
    private final GeneratorManager manager;

    private Initializer() {
        this.manager = new GeneratorManager(this);
    }

    public static Initializer getCurrentInitializer() {
        return INITIALIZER;
    }

    public Node[][] generate(Level level) {
        return manager.generate(level);
    }

    @Override
    public int getDrawable(int index) {
        switch (index) {
            case 1:
                return R.drawable.face_1;
            case 2:
                return R.drawable.face_2;
            case 3:
                return R.drawable.face_3;
            case 4:
                return R.drawable.face_4;
            case 5:
                return R.drawable.face_5;
            case 6:
                return R.drawable.face_6;
            case 7:
                return R.drawable.face_7;
            case 8:
                return R.drawable.face_8;
            case 9:
                return R.drawable.face_9;
            case 10:
                return R.drawable.face_10;
            case 11:
                return R.drawable.face_11;
            case 12:
                return R.drawable.face_12;
        }

        return R.drawable.face_1;
    }

    public static int[] drawables = {
            R.drawable.face_1, R.drawable.face_2, R.drawable.face_3, R.drawable.face_4,
            R.drawable.face_5, R.drawable.face_6, R.drawable.face_7, R.drawable.face_8,
            R.drawable.face_9, R.drawable.face_10, R.drawable.face_11, R.drawable.face_12
    };
}
