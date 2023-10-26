/**
 * 
 */
package coffprocesscv;

import java.io.IOException;
import java.io.RandomAccessFile;

import coffprocesscv.pdbreader.PdbByteReader;
import coffprocesscv.pdbreader.PdbException;
import coffprocesscv.pdbreader.PdbReaderOptions;
import coffprocesscv.pdbreader.msf.AbstractMsf;
import ghidra.util.task.TaskMonitor;

/**
 * 
 */
public class DummyMsf extends AbstractMsf {

	/**
	 * @param program 
	 * @param file
	 * @param filename
	 * @param monitor
	 * @param pdbOptions
	 * @throws IOException
	 * @throws PdbException
	 */
	public DummyMsf(RandomAccessFile file, String filename, TaskMonitor monitor, PdbReaderOptions pdbOptions)
			throws IOException, PdbException {
		super(file, filename, monitor, pdbOptions);
	}

	@Override
	public byte[] getIdentification() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPageSizeOffset() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void parseFreePageMapPageNumber(PdbByteReader reader) throws PdbException {
		// TODO Auto-generated method stub

	}

	@Override
	public void parseCurrentNumPages(PdbByteReader reader) throws PdbException {
		// TODO Auto-generated method stub

	}

	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

	@Override
	public void configureParameters() throws PdbException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getPageNumberSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
