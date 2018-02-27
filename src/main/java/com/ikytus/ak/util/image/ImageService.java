package com.ikytus.ak.util.image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ikytus.ak.domain.AbstractEntity;
import com.ikytus.ak.services.AbstractService;
import com.ikytus.ak.util.customException.FileException;

@Service
public class ImageService {

	@Autowired
	private ServletContext context;

	public String imagemString;

	public Path path;

	public static String encodeImagem(byte[] imageByteArray) {
		return Base64.encodeBase64String(imageByteArray);
	}

	public String gravarImagemBase64(MultipartFile file) {
		try {
			imagemString = encodeImagem(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imagemString;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String gravaImagemBase64Service(MultipartFile file, AbstractService service, AbstractEntity entidade) {

		if (gravarImagemBase64(file).isEmpty()) {
			service.salvar(entidade);
		} else {
			imagemString = "data:image/png;base64," + gravarImagemBase64(file);
			service.salvar(entidade, imagemString);
		}
		return gravarImagemBase64(file);
	}

	public String gravarImagemArquivo(MultipartFile file, String pasta) {

		String nomeImagem = file.getOriginalFilename();

		if (nomeImagem.isEmpty()) {
			return nomeImagem;
		} else {
			try {
				byte[] bytes = file.getBytes();
				path = Paths.get(context.getRealPath("/img/") + pasta + "/" + nomeImagem);
				Files.write(path, bytes);

			} catch (IOException e) {
				e.printStackTrace();
			}
			return nomeImagem;
		}
	}

	public String recuperarImagemArquivo(String pasta, String arquivo) throws IOException {

		path = Paths.get(context.getRealPath("/img/") + pasta);

		File file = new File(path + "/" + arquivo);

		// Reading a Image file from file system
		FileInputStream imageInFile = new FileInputStream(file);
		byte imageData[] = new byte[(int) file.length()];
		imageInFile.read(imageData);
		imageInFile.close();

		// Converting Image byte array into Base64 String
		imagemString = "data:image/png;base64," + encodeImagem(imageData);

		return imagemString;
	}

	public void criarPasta(String pasta) {
		path = Paths.get(context.getRealPath("/"));
		File file = new File(path + File.separator + pasta);
		file.mkdirs();
	}

	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {
		String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
		if (!"png".equals(ext) && !"jpg".equals(ext)) {
			throw new FileException("Somente imagens PNG e JPG são permitidas");
		}
		try {
			BufferedImage img = ImageIO.read(uploadedFile.getInputStream());
			if ("png".equals(ext)) {
				img = pngToJpg(img);
			}
			return img;
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo");
		}
	}

	private BufferedImage pngToJpg(BufferedImage img) {
		BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		return jpgImage;
	}

	public InputStream getInputStream(BufferedImage img, String extension) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, extension, os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo");
		}
	}

	public BufferedImage cropSquare(BufferedImage sourceImg) {
		int min = (sourceImg.getHeight() <= sourceImg.getWidth()) ? sourceImg.getHeight() : sourceImg.getWidth();
		return Scalr.crop(sourceImg, (sourceImg.getWidth() / 2) - (min / 2), (sourceImg.getHeight() / 2) - (min / 2),
				min, min);
	}

	public BufferedImage resize(BufferedImage sourceImg, int size) {
		return Scalr.resize(sourceImg, Scalr.Method.ULTRA_QUALITY, size);
	}
}
