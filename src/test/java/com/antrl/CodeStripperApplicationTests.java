package com.antrl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.antrl.recognizer.java.JavaLexer;
import com.antrl.recognizer.java.JavaParser;
import com.antrl.recognizer.java.listener.JavaListener;

@SpringBootTest
class CodeStripperApplicationTests {

	@Test
	void contextLoads() throws IOException {
		// iteración sobre varias clases, etc
		String inputFile = null;

		// Fuerza la carga del fichero de pruebas
		inputFile = "src/test/java/com/antrl/examples/Persona.java";

		InputStream is = System.in;
		if (inputFile != null)
			is = new FileInputStream(inputFile);

		CharStream input = CharStreams.fromStream(is);
		JavaLexer lexer = new JavaLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		JavaParser parser = new JavaParser(tokens);
		parser.setBuildParseTree(true);
		ParseTree tree = parser.compilationUnit();
		// System.out.println(tree.toStringTree(parser));
		JavaListener ln = new JavaListener();
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(ln, tree);

	}

}
