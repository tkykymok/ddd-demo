package com.example.demo.infrastructure.support;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class SQLLoader {
    /**
     * SQLファイルを読み込み、その内容を文字列として返します。
     *
     * @param fileName クラスパス内のリソースディレクトリからのSQLファイル名
     * @return ファイルの内容を含む文字列
     * @throws IOException ファイルが見つからないか、読み込み中にエラーが発生した場合
     */
    public String loadSQL(String fileName) throws IOException {
        // "sql/"ディレクトリ内の指定されたファイルをクラスパスリソースとしてロードします。
        Resource resource = new ClassPathResource("sql/" + fileName);
        // リソースのInputStreamを取得し、その内容をUTF-8エンコーディングで読み込みます。
        try (InputStream inputStream = resource.getInputStream()) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
