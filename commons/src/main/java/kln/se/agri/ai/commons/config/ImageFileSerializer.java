package kln.se.agri.ai.commons.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import kln.se.agri.ai.commons.model.ImageFile;

import java.io.IOException;

public class ImageFileSerializer extends StdSerializer<ImageFile> {
    public ImageFileSerializer() {
    this(null);
    }

    public ImageFileSerializer(Class<ImageFile> t) {
    super(t);
    }

    @Override
    public void serialize(ImageFile value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException
    {
        jgen.writeStartObject();
//        jgen.writeBinaryField("file", value.getFile());
        jgen.writeNumberField("id",value.getId());
        jgen.writeStringField("mimeType", value.getMimeType());
        jgen.writeStringField("description", value.getDescription());
//        jgen.writeNumberField("artifactID", value.getArtifactID());
//        jgen.writeStringField("artifactType", value.getArtifactType());
        jgen.writeStringField("filename", value.getFilename(value.getId()));
        jgen.writeStringField("pictureName", value.getPictureName());
//        jgen.writeBinaryField("thumbnail", value.getThumbnail());
        jgen.writeEndObject();
    }

}
