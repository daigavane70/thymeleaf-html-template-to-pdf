package com.starter.sprint.service;

import java.io.File;
import java.util.Map;

public interface PdfGenerateService {
    File generatePdfFile(String templateName, Map<String, Object> data, String pdfFileName);
}
