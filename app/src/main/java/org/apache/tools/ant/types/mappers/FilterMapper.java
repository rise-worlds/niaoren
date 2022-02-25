package org.apache.tools.ant.types.mappers;

import java.io.StringReader;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.UnsupportedAttributeException;
import org.apache.tools.ant.filters.util.ChainReaderHelper;
import org.apache.tools.ant.types.FilterChain;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.FileUtils;

/* loaded from: classes2.dex */
public class FilterMapper extends FilterChain implements FileNameMapper {
    private static final int BUFFER_SIZE = 8192;

    @Override // org.apache.tools.ant.util.FileNameMapper
    public void setFrom(String str) {
        throw new UnsupportedAttributeException("filtermapper doesn't support the \"from\" attribute.", "from");
    }

    @Override // org.apache.tools.ant.util.FileNameMapper
    public void setTo(String str) {
        throw new UnsupportedAttributeException("filtermapper doesn't support the \"to\" attribute.", "to");
    }

    @Override // org.apache.tools.ant.util.FileNameMapper
    public String[] mapFileName(String str) {
        try {
            StringReader stringReader = new StringReader(str);
            ChainReaderHelper chainReaderHelper = new ChainReaderHelper();
            chainReaderHelper.setBufferSize(8192);
            chainReaderHelper.setPrimaryReader(stringReader);
            chainReaderHelper.setProject(getProject());
            Vector<FilterChain> vector = new Vector<>();
            vector.add(this);
            chainReaderHelper.setFilterChains(vector);
            String safeReadFully = FileUtils.safeReadFully(chainReaderHelper.getAssembledReader());
            if (safeReadFully.length() == 0) {
                return null;
            }
            return new String[]{safeReadFully};
        } catch (BuildException e) {
            throw e;
        } catch (Exception e2) {
            throw new BuildException(e2);
        }
    }
}
