package com.leaflet.gameapp.domain.communicator;

import android.widget.ImageView;

/**
 * Created by yasser.ibrahim on 5/8/2018.
 */

public interface Clickable {
    void onClick(ImageView image, int row, int column);
}
