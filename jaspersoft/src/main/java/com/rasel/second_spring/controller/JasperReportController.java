package com.rasel.second_spring.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Controller
public class JasperReportController {
	@Autowired
	private DataSource dataSource;

	@GetMapping("/employee")
	public void generateEmployeeReport(HttpServletResponse response) throws Exception {
		InputStream reportStream = getClass().getResourceAsStream("/reports/Blank_A4_2.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

		try (Connection conn = dataSource.getConnection()) {
			Map<String, Object> parameters = new HashMap<>();
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

			response.setContentType(MediaType.APPLICATION_PDF_VALUE);
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employee_report.pdf");

			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
		}
	}
}
