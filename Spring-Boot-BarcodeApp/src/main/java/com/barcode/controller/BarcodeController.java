
package com.barcode.controller;

import java.awt.image.BufferedImage;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barcode.generator.BarbecueBarcodeGenerator;
import com.barcode.generator.Barcode4jBarcodeGenerator;
import com.barcode.generator.QRGenBarcodeGenerator;
import com.barcode.generator.ZxingBarcodeGenerator;

@RestController

@RequestMapping("/barcodes")
public class BarcodeController {

	// Barbecue library

	// http://localhost:8080/barcodes/barbecue/upca/12345678901
	@GetMapping(value = "/barbecue/upca/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<BufferedImage> barbecueUPCABarcode(@PathVariable("barcode") String barcode) throws Exception {
		return new ResponseEntity<BufferedImage>(BarbecueBarcodeGenerator.generateUPCABarcodeImage(barcode),
				HttpStatus.OK);
	}

	// http://localhost:8080/barcodes/barbecue/ean13/012345678901
	@GetMapping(value = "/barbecue/ean13/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<BufferedImage> barbecueEAN13Barcode(@PathVariable("barcode") String barcode)
			throws Exception {
		return new ResponseEntity<BufferedImage>(BarbecueBarcodeGenerator.generateEAN13BarcodeImage(barcode),
				HttpStatus.OK);
	}

	// http://localhost:8080/barcodes/barbecue/code128
	@PostMapping(value = "/barbecue/code128", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<BufferedImage> barbecueCode128Barcode(@RequestBody String barcode) throws Exception {
		return new ResponseEntity<BufferedImage>(BarbecueBarcodeGenerator.generateCode128BarcodeImage(barcode),
				HttpStatus.OK);
	}

	// http://localhost:8080/barcodes/barbecue/pdf417
	@PostMapping(value = "/barbecue/pdf417", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<BufferedImage> barbecuePDF417Barcode(@RequestBody String barcode) throws Exception {
		return new ResponseEntity<BufferedImage>(BarbecueBarcodeGenerator.generatePDF417BarcodeImage(barcode),
				HttpStatus.OK);
	}

	// Barcode4j library

	@GetMapping(value = "/barcode4j/upca/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<BufferedImage> barcode4jUPCABarcode(@PathVariable("barcode") String barcode) {
		return new ResponseEntity<BufferedImage>(Barcode4jBarcodeGenerator.generateUPCABarcodeImage(barcode),
				HttpStatus.OK);
	}

	@GetMapping(value = "/barcode4j/ean13/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<BufferedImage> barcode4jEAN13Barcode(@PathVariable("barcode") String barcode) {
		return new ResponseEntity<BufferedImage>(Barcode4jBarcodeGenerator.generateEAN13BarcodeImage(barcode),
				HttpStatus.OK);
	}

	@PostMapping(value = "/barcode4j/code128", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<BufferedImage> barcode4jCode128Barcode(@RequestBody String barcode) {
		return new ResponseEntity<BufferedImage>(Barcode4jBarcodeGenerator.generateCode128BarcodeImage(barcode),
				HttpStatus.OK);
	}

	@PostMapping(value = "/barcode4j/pdf417", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<BufferedImage> barcode4jPDF417Barcode(@RequestBody String barcode) {
		return new ResponseEntity<BufferedImage>(Barcode4jBarcodeGenerator.generatePDF417BarcodeImage(barcode),
				HttpStatus.OK);
	}

	// Zxing library

	@GetMapping(value = "/zxing/upca/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<BufferedImage> zxingUPCABarcode(@PathVariable("barcode") String barcode) throws Exception {
		return new ResponseEntity<BufferedImage>(ZxingBarcodeGenerator.generateUPCABarcodeImage(barcode),
				HttpStatus.OK);
	}

	@GetMapping(value = "/zxing/ean13/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<BufferedImage> zxingEAN13Barcode(@PathVariable("barcode") String barcode) throws Exception {
		return new ResponseEntity<BufferedImage>(ZxingBarcodeGenerator.generateEAN13BarcodeImage(barcode),
				HttpStatus.OK);
	}

	@PostMapping(value = "/zxing/code128", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<BufferedImage> zxingCode128Barcode(@RequestBody String barcode) throws Exception {
		return new ResponseEntity<BufferedImage>(ZxingBarcodeGenerator.generateCode128BarcodeImage(barcode),
				HttpStatus.OK);
	}

	@PostMapping(value = "/zxing/pdf417", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<BufferedImage> zxingPDF417Barcode(@RequestBody String barcode) throws Exception {
		return new ResponseEntity<BufferedImage>(ZxingBarcodeGenerator.generatePDF417BarcodeImage(barcode),
				HttpStatus.OK);
	}

	@PostMapping(value = "/zxing/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<BufferedImage> zxingQRCode(@RequestBody String barcode) throws Exception {
		return new ResponseEntity<BufferedImage>(ZxingBarcodeGenerator.generateQRCodeImage(barcode), HttpStatus.OK);
	}

	//QRGen
    @PostMapping(value = "/qrgen/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> qrgenQRCode(@RequestBody String barcode) throws Exception {
        return okResponse(QRGenBarcodeGenerator.generateQRCodeImage(barcode));
    }

    private ResponseEntity<BufferedImage> okResponse(BufferedImage image) {
        return new ResponseEntity<>(image, HttpStatus.OK);
    }
}
