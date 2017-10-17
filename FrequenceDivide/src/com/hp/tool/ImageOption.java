package com.hp.tool;
/**
 * 压缩和处理图片的工具类
 */
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/*
 * 图片压缩处理
 */
public class ImageOption {
	public static Bitmap getBitmapFromResource(Context context, int resId) {
		InputStream is = context.getResources().openRawResource(resId);
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = false;
		options.inSampleSize = 2;
		// width，hight设为原来的二分一
		return BitmapFactory.decodeStream(is, null, options);
	}

	public static int computeSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		int initialSize = computeInitialSampleSize(options, minSideLength,
				maxNumOfPixels);

		int roundedSize;
		if (initialSize <= 8) {
			roundedSize = 1;
			while (roundedSize < initialSize) {
				roundedSize <<= 1;
			}
		} else {
			roundedSize = (initialSize + 7) / 8 * 8;
		}
		return roundedSize;
	}

	private static int computeInitialSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		double w = options.outWidth;
		double h = options.outHeight;

		int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
				.sqrt(w * h / maxNumOfPixels));
		int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
				Math.floor(w / minSideLength), Math.floor(h / minSideLength));

		if (upperBound < lowerBound) {
			return lowerBound;
		}

		if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
			return 1;
		} else if (minSideLength == -1) {
			return lowerBound;
		} else {
			return upperBound;
		}
	}

	public static void bitmapRecycled(Bitmap bitmap) {
		if (!bitmap.isRecycled()) {
			bitmap.recycle(); // 回收图片所占的内存
			System.gc(); // 提醒系统及时回收
		}
	}
}
