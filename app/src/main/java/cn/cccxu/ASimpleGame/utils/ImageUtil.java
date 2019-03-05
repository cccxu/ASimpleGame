package cn.cccxu.ASimpleGame.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import cn.cccxu.ASimpleGame.R;
import cn.cccxu.ASimpleGame.view.PieceImage;

public class ImageUtil {

    private static List<Integer> imageValues = getImageValues();

    private static List<Integer> getImageValues() {
        try {
            // 得到R.drawable所有的属性, 即获取drawable目录下的所有图片
            Field[] drawableFields = R.drawable.class.getFields();
            List<Integer> resourceValues = new ArrayList<Integer>();
            for (Field field : drawableFields) {
                // 如果该Field的名称以fruit_开头
                if (field.getName().indexOf("cell") != -1) {
                    resourceValues.add(field.getInt(R.drawable.class));
                }
            }
            return resourceValues;
        } catch (Exception e) {
            return null;
        }
    }

    private static List<Integer> getRandomValues(List<Integer> sourceValues,
                                                 int size) {
        // 创建一个随机数生成器
        Random random = new Random();
        // 创建结果集合
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            try {
                // 随机获取一个数字，大于、小于sourceValues.size()的数值
                int index = random.nextInt(sourceValues.size());
                // 从图片ID集合中获取该图片对象
                Integer image = sourceValues.get(index);
                // 添加到结果集中
                result.add(image);
            } catch (IndexOutOfBoundsException e) {
                return result;
            }
        }
        return result;
    }

    private static List<Integer> getPlayValues(int size) {
        if (size % 2 != 0) {
            // 如果该数除2有余数，将size加1
            size += 1;
        }
        List<Integer> playImageValues = getRandomValues(imageValues, size / 2);
        playImageValues.addAll(playImageValues);
        Collections.shuffle(playImageValues);
        return playImageValues;
    }

    public static List<PieceImage> getPlayImages(Context context, int size) {
        List<Integer> resourceValues = getPlayValues(size);
        List<PieceImage> result = new ArrayList<PieceImage>();
        for (Integer value : resourceValues) {
            Bitmap bm = drawableToBitmap(context.getResources().getDrawable(value));
            PieceImage pieceImage = new PieceImage(bm, value);
            result.add(pieceImage);
        }
        return result;
    }

    private static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(GameConf.PIECE_WIDTH, GameConf.PIECE_HEIGHT, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, GameConf.PIECE_WIDTH, GameConf.PIECE_HEIGHT);
        drawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap getSelectImage(Context context) {
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.selected);
    }
}
