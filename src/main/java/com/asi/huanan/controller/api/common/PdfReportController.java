package com.asi.huanan.controller.api.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RestController;

import com.asi.mechanism.SpringProperty;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@RestController
public class PdfReportController {
	
	private static Logger log = LogManager.getLogger(PdfReportController.class);
	
	/**
	 * 取得PDF byte[]資料
	 * @param parameters
	 * @param datasource
	 * @param jrxmlName
	 * @param jpgName
	 * @return
	 * @throws IOException
	 */
	public byte[] getPdfBytes(Map<String, Object> parameters, List<?> datasource, String jrxmlName,
			String jpgName) throws IOException {

		JasperReport jasperReport;
		JasperPrint print = new JasperPrint();
		byte[] pdfByteArr = null;
		try {
			ClassPathResource classPathResource = new ClassPathResource(
					"templates/ireport/jrxml/" + jrxmlName + ".jrxml");
			InputStream inputStream = classPathResource.getInputStream();
			JasperDesign design = JRXmlLoader.load(inputStream);
			if (!"".equals(jpgName)) {
				ClassPathResource jpgPathResource = new ClassPathResource("templates/ireport/jpg/" + jpgName + ".jpg");
				InputStream inputStream2 = jpgPathResource.getInputStream();
				parameters.put("image", inputStream2);
			}
			jasperReport = JasperCompileManager.compileReport(design);
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(datasource);
			print = JasperFillManager.fillReport(jasperReport, parameters, ds);
			pdfByteArr = JasperExportManager.exportReportToPdf(print);
			
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sub -> log.debug(sub.toString()));
		}
		
		return pdfByteArr;			
	}
	
	/**
	 * 輸出報表至配置檔設定的路徑
	 * @param parameters
	 * @param datasource
	 * @param jrxmlName
	 * @param jpgName
	 * @param pdfName
	 * @return
	 * @throws IOException
	 */	
		public void printReport(Map<String, Object> parameters, List<?> datasource, String jrxmlName, String jpgName, String pdfName) throws IOException{

			JasperReport jasperReport;

			JasperPrint print = new JasperPrint();
			

			try {

				ClassPathResource classPathResource = new ClassPathResource("templates/ireport/jrxml/" + jrxmlName + ".jrxml");
				InputStream inputStream = classPathResource.getInputStream();
				JasperDesign design = JRXmlLoader.load(inputStream);

				if(!"".equals(jpgName)) {
					ClassPathResource jpgPathResource = new ClassPathResource("templates/ireport/jpg/" + jpgName + ".jpg");
					InputStream inputStream2 = jpgPathResource.getInputStream();
					parameters.put("image", inputStream2);
				}

				jasperReport = JasperCompileManager.compileReport(design);
				JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(datasource);
				print = JasperFillManager.fillReport(jasperReport,parameters,ds);
				//資料夾路徑
				String directoryPath = SpringProperty.localFileForPdf;
				//判別資料夾是否存在，若不存在則建立
				File directory = new File(directoryPath);
				if(!directory.isFile()) {
					directory.mkdirs();
				}
				//報表檔案路徑
				String pdfPath = directoryPath+pdfName;
				log.debug("報表路徑="+pdfPath);
				JasperExportManager.exportReportToPdfFile(print, pdfPath);

			} catch (JRException e) {
				log.debug(e.toString());
				Arrays.asList(e.getStackTrace()).stream().forEach(sub -> log.debug(sub.toString()));
			}
		}
}
