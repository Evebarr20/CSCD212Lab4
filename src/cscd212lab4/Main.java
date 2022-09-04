package cscd212lab4;

import com.raylib.Jaylib;
import com.raylib.Raylib;
import cscd212classes.lab4.User;
import cscd212classes.lab4.map.Map;
import cscd212classes.lab4.map.MapFactory;
import cscd212lib.lab2.ConsoleColors;
import cscd212classes.lab3.GameMaster;
import cscd212lib.lab4.Display;

import java.util.Scanner;

import static com.raylib.Jaylib.BLACK;
import static com.raylib.Jaylib.BLANK;
import static com.raylib.Jaylib.BeginDrawing;
import static com.raylib.Jaylib.ClearBackground;
import static com.raylib.Jaylib.CloseAudioDevice;
import static com.raylib.Jaylib.CloseWindow;
import static com.raylib.Jaylib.DrawRectangle;
import static com.raylib.Jaylib.EndDrawing;
import static com.raylib.Jaylib.FLAG_WINDOW_HIGHDPI;
import static com.raylib.Jaylib.FLAG_WINDOW_TOPMOST;
import static com.raylib.Jaylib.FLAG_WINDOW_TRANSPARENT;
import static com.raylib.Jaylib.Image;
import static com.raylib.Jaylib.InitAudioDevice;
import static com.raylib.Jaylib.InitWindow;
import static com.raylib.Jaylib.IsCursorOnScreen;
import static com.raylib.Jaylib.LoadImage;
import static com.raylib.Jaylib.LoadMusicStream;
import static com.raylib.Jaylib.Music;
import static com.raylib.Jaylib.PlayMusicStream;
import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Jaylib.SetConfigFlags;
import static com.raylib.Jaylib.SetMusicVolume;
import static com.raylib.Jaylib.SetWindowIcon;
import static com.raylib.Jaylib.UnloadMusicStream;
import static com.raylib.Jaylib.UpdateMusicStream;
import static com.raylib.Jaylib.WHITE;
import static com.raylib.Jaylib.WindowShouldClose;
import static com.raylib.Raylib.DrawText;

public class Main {

    private static final float MUSIC_VOLUME = 0.04f;
    private static final boolean BACKGROUND_IS_TRANSPARENT = true;

    private static GameMaster gm;
    private static Map map;
    private static User user;

    private static Display display;

    private static String mapString;

    /**
     * The start of this program
     *
     * @param args argument past in to this program
     */
    public static void main(final String[] args) {
        // Pick a map
        Scanner kb = new Scanner(System.in);
        String[] mapStrings = MapFactory.getMapStrings();
        int choice = -1;
        while (choice < 0 || choice > mapStrings.length) {
            System.out.println("--- Pick a Map ---");
            for (int i = 0; i < mapStrings.length; i++) {
                System.out.println(i + " : " + mapStrings[i]);
            }
            System.out.print("> ");
            choice = Integer.parseInt(kb.nextLine());
        }
        mapString = mapStrings[choice];

        // Ray-lib (GUI Main)
        System.out.println(
                ConsoleColors.RED +
                "Warring RayLib(JayLib) will print it's logs when its window close" +
                ConsoleColors.RESET
        );
        System.out.println("Launching Ray-lib");
        guiMain();
    }

    /**
     * runs the gui (raylib)
     * <br> on right click in window to run next
     * @NOTE most of this code is base off and calls of c and cpp code
     */
    private static void guiMain() {
        final int[] windowSize = new int[]{800, 512};

        setUpGame(windowSize);

        setUpWindowInMain(windowSize);

        Music music = setupSoundAndMusic();

        while (!WindowShouldClose()) {
            // Updates
            UpdateMusicStream(music);

            // User input check
            if (IsCursorOnScreen()) {
                user.getUserInput();
            }

            // Screen
            BeginDrawing();
                ClearBackground(BLANK);

                // Map
                for (int x = 0; x < map.getNumOfCols(); x++) {
                    for (int y = 0; y < map.getNumOfRows(); y++) {
                        drawMapBox(x, y);
                    }
                }

                // Options
                drawOptionButtonLeft();
                drawOptionBoxMiddle();
                drawOptionButtonRight();

            EndDrawing();
        }

        UnloadMusicStream(music);

        CloseAudioDevice();

        CloseWindow();
    }

    /**
     * Used to draw Option Button Right
     */
    private static void drawOptionButtonRight() {
        DrawRectangle(
                display.getOptionsButtonRightX(),
                display.getOptionsBoxY(),
                display.getOptionsButtonWidth(),
                display.getOptionsBoxHeight(),
                WHITE
        );
        DrawText(
                "Ability",
                display.getOptionsButtonRightX() + 10,
                display.getOptionsBoxY() + 15,
                18,
                BLACK
        );
    }

    /**
     * Used to draw Option Box Middle
     */
    private static void drawOptionBoxMiddle() {
        DrawRectangle(
                display.getOptionsBoxMiddleX(),
                display.getOptionsBoxY(),
                display.getOptionsBoxMiddleWidth(),
                display.getOptionsBoxHeight(),
                RAYWHITE
        );
        DrawText(
                display.getInfoBoxMessage(),
                display.getOptionsBoxMiddleX() + 10,
                display.getOptionsBoxY() + 15,
                18,
                BLACK
        );
    }

    /**
     * Used to draw Option Button Left
     */
    private static void drawOptionButtonLeft() {
        DrawRectangle(
                display.getOptionsButtonLeftX(),
                display.getOptionsBoxY(),
                display.getOptionsButtonWidth(),
                display.getOptionsBoxHeight(),
                WHITE
        );
        DrawText(
                "Move",
                display.getOptionsButtonLeftX() + 10,
                display.getOptionsBoxY() + 15,
                18,
                BLACK
        );
    }

    /**
     * Used to draw a map box (latter plan to replace with sprites)
     * @param x the map x
     * @param y the map y
     */
    private static void drawMapBox(final int x, final int y) {
        Raylib.Color color;
        if (map.getAgent(x, y) != null) {
            color = map.getAgent(x, y).getColor();
        } else {
            color = new Jaylib.Color(255, 255, 255, 100);
        }
        DrawRectangle(
                display.getBoxDisplayX(x),
                display.getBoxDisplayY(y),
                display.getBoxWidth(),
                display.getBoxHeight(),
                color
        );
        if (map.getAgent(x, y) != null) {
            DrawText(
                    map.getAgent(x, y).getClass().getSimpleName(),
                    display.getBoxDisplayX(x) + 10,
                    display.getBoxDisplayY(y) + 10,
                    10,
                    BLACK
            );
        }
    }

    /**
     * Setup music (raylib)
     * @return the control for the music
     */
    private static Music setupSoundAndMusic() {
        InitAudioDevice();

        Music music = LoadMusicStream("res/music/Loops/OveMelaa - Trance Bit Bit Loop.ogg");
        SetMusicVolume(music, MUSIC_VOLUME);
        PlayMusicStream(music);
        return music;
    }

    /**
     * Set up the window for the gui (raylib)
     * @param windowSize the size of the window
     */
    private static void setUpWindowInMain(final int[] windowSize) {
        SetConfigFlags(FLAG_WINDOW_HIGHDPI);
        if (BACKGROUND_IS_TRANSPARENT) {
            SetConfigFlags(FLAG_WINDOW_TRANSPARENT);
        }
        SetConfigFlags(FLAG_WINDOW_TOPMOST);
        InitWindow(windowSize[0], windowSize[1], "EWU CSCD 212 Game - Lab4");

        Image logo = LoadImage("res/icon/eagle.png");
        SetWindowIcon(logo);
    }

    /**
     * Setting up the game
     * @param windowSize the window size (width, height) as int array of size 2
     */
    private static void setUpGame(final int[] windowSize) {
        gm = GameMaster.getGameMaster();
        map = MapFactory.getMap(mapString);
        display = new Display(windowSize);
        display.setMap(map);
        user = User.getUser(display, map);
        gm.setMap(map);
    }
}
