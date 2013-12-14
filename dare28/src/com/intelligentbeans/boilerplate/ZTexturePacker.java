package com.intelligentbeans.boilerplate;
import com.badlogic.gdx.tools.imagepacker.TexturePacker;
import com.badlogic.gdx.tools.imagepacker.TexturePacker.Settings;

/**
 * Packs single images into image atlases.
 */
@SuppressWarnings("deprecation")
public class ZTexturePacker
{
    private static final String INPUT_DIR = "../dare28-android/assets/data/z/";
    private static final String OUTPUT_DIR = "../dare28-android/assets/data/z/";
    private static final String PACK_FILE = "dare-textures";
    private static final String INPUT_DIRMENU = "../dare28-android/assets/data/menu/";
    private static final String OUTPUT_DIRMENU = "../dare28-android/assets/data/menu/";
    private static final String PACK_FILEMENU = "dare28-textures";

    public static void main()
    {
        // create the packing's settings
        Settings settings = new Settings();

        // adjust the padding settings
        settings.padding = 2;
        settings.edgePadding = false;

        // set the maximum dimension of each image atlas
        settings.maxWidth = 2048;
        settings.maxWidth = 2048;

        // don't repack a group when no changes were made to it
        settings.incremental = true;

        // pack the images
        TexturePacker.process( settings, INPUT_DIR, OUTPUT_DIR, PACK_FILE );
        TexturePacker.process( settings, INPUT_DIRMENU, OUTPUT_DIRMENU, PACK_FILEMENU );
    }
}
