package com.jingcheng.musicEducation.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeUtil {
	/**
	 * 生成包含字符串信息的二维码图片
	 * 
	 * @param outputStream
	 *            文件输出流路径
	 * @param content
	 *            二维码携带信息
	 * @param qrCodeSize
	 *            二维码图片大小
	 * @param imageFormat
	 *            二维码的格式
	 * @throws WriterException
	 * @throws IOException
	 */
	public static boolean createQrCode(OutputStream outputStream, String content, int qrCodeSize, String imageFormat)
			throws WriterException, IOException {
		// 设置二维码纠错级别ＭＡＰ
		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L); // 矫错级别
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		// 创建比特矩阵(位矩阵)的QR码编码的字符串
		BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hintMap);
		// 使BufferedImage勾画QRCode (matrixWidth 是行二维码像素点)
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth - 200, matrixWidth - 200, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();
		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// 使用比特矩阵画并保存图像
		graphics.setColor(Color.BLACK);
		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i - 100, j - 100, 1, 1);
				}
			}
		}
		return ImageIO.write(image, imageFormat, outputStream);
	}

	/**
	 * 生成二维码图片 不存储 直接以流的形式输出到页面
	 * 
	 * @param content
	 * @param response
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void encodeQrcode(String content, HttpServletResponse response) {
		if (content == null || "".equals(content))
			return;
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		Map hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); // 设置字符集编码类型
		BitMatrix bitMatrix = null;
		try {
			bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 300, 300, hints);
			int matrixWidth = bitMatrix.getWidth();
			BufferedImage image = new BufferedImage(matrixWidth - 200, matrixWidth - 200, BufferedImage.TYPE_INT_RGB);
			image.createGraphics();
			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, matrixWidth, matrixWidth);
			// 使用比特矩阵画并保存图像
			graphics.setColor(Color.BLACK);
			for (int i = 0; i < matrixWidth; i++) {
				for (int j = 0; j < matrixWidth; j++) {
					if (bitMatrix.get(i, j)) {
						graphics.fillRect(i - 100, j - 100, 1, 1);
					}
				}
			}
			// 输出二维码图片流
			try {
				ImageIO.write(image, "png", response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (WriterException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 读二维码并输出携带的信息
	 */
	public static Result readQrCode(String path) throws IOException {
		File file = new File(path);
		InputStream inputStream = new FileInputStream(file);
		// 从输入流中获取字符串信息
		BufferedImage image = ImageIO.read(inputStream);
		// 将图像转换为二进制位图源
		LuminanceSource source = new BufferedImageLuminanceSource(image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		QRCodeReader reader = new QRCodeReader();
		Result result = null;
		try {
			result = reader.decode(bitmap);
		} catch (ReaderException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 测试代码
	 * 
	 * @throws WriterException
	 */
	public static void main(String[] args) throws IOException, WriterException {
		String uuid = UUID.randomUUID().toString();
		/*
		 * String url=
		 * "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxec9df541f9561e47&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=STATE&uuid=UUID#wechat_redirect";
		 */
		String url = "http://192.168.0.188:8080/bawj1/rest/login/authorization?uuit=" + uuid;
		String qrType = "biai-qrCode";
		// String content=url+",qrType="+qrType;
		JsonObject json = new JsonObject();
		json.addProperty("url", url);
		json.addProperty("qrType", qrType);
		System.out.println(json);
		// json.add("content", json);
		String content = json.toString();
		System.out.println(content);
		JsonParser parser = new JsonParser();
		JsonElement root = parser.parse(content);
		JsonObject sjson = root.getAsJsonObject();
		String qrType1 = sjson.getAsJsonPrimitive("qrType").getAsString();
		System.out.println(qrType1);
		/*
		 * String[] arr=content.split(","); for(int i=0;i<arr.length;i++){
		 * String str=arr[i];
		 * 
		 * System.out.println(str); }
		 */

		/*
		 * String imgName=uuid+".jpg"; //生成的图片访问地址 String qrCodeImg =
		 * "http://192.168.0.126:8080/qrlogin/" + imgName; JsonObject json=new
		 * JsonObject(); json.addProperty("uuid", uuid);
		 * json.addProperty("qrCodeImg", qrCodeImg); String jsonStr
		 * =json.toString(); System.out.println(jsonStr);
		 */

		/*
		 * createQrCode(new FileOutputStream(new File("d:\\qrcode.jpg")),
		 * "WE1231238239128sASDASDSADSDWEWWREWRERWSDFDFSDSDF123123123123213123",
		 * 900,"JPEG"); readQrCode(new FileInputStream(new
		 * File("d:\\qrcode.jpg")));
		 */
	}

}
