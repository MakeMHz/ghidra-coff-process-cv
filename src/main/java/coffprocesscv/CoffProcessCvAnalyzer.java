/* ###
 * IP: GHIDRA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package coffprocesscv;

import java.io.IOException;
import coffprocesscv.pdbapplicator.DefaultPdbApplicator;
import coffprocesscv.pdbapplicator.PdbApplicatorControl;
import coffprocesscv.pdbapplicator.PdbApplicatorOptions;
import coffprocesscv.pdbreader.PdbException;
import coffprocesscv.pdbreader.PdbReaderOptions;
import coffprocesscv.pdbreader.EmbeddedCodeViewPdb;
import ghidra.app.services.AbstractAnalyzer;
import ghidra.app.services.AnalysisPriority;
import ghidra.app.services.AnalyzerType;
import ghidra.app.util.importer.MessageLog;
import ghidra.app.util.opinion.MSCoffLoader;
import ghidra.framework.options.Options;
import ghidra.program.model.address.AddressSetView;
import ghidra.program.model.listing.Program;
import ghidra.util.exception.CancelledException;
import ghidra.util.task.TaskMonitor;

/**
 * TODO: Provide class-level documentation that describes what this analyzer
 * does.
 */
public class CoffProcessCvAnalyzer extends AbstractAnalyzer {
	public CoffProcessCvAnalyzer() {
		super("COFF Codeview Analyzer", "Analyzer description goes here", AnalyzerType.BYTE_ANALYZER);

		//
		setPriority(AnalysisPriority.HIGHEST_PRIORITY);
	}

	@Override
	public boolean getDefaultEnablement(Program program) {
		return false;
	}

	@Override
	public boolean canAnalyze(Program program) {
		return program.getExecutableFormat().equals(MSCoffLoader.MSCOFF_NAME);
	}

	@Override
	public void registerOptions(Options options, Program program) {
		// TODO: If this analyzer has custom options, register them here
		//options.registerOption("Option name goes here", false, null,
		//		"Option description goes here");
	}

	@Override
	public boolean added(Program program, AddressSetView set, TaskMonitor monitor, MessageLog log)
			throws CancelledException {

		try {
			DummyMsf dummyMsf = new DummyMsf(null, "CodeView", monitor, new PdbReaderOptions());
			EmbeddedCodeViewPdb dummyPDB = new EmbeddedCodeViewPdb(program, dummyMsf, new PdbReaderOptions());

			dummyPDB.deserialize();
			
			PdbApplicatorOptions applicatorOptions = new PdbApplicatorOptions();
			applicatorOptions.setProcessingControl(PdbApplicatorControl.ALL);

			DefaultPdbApplicator applicator = new DefaultPdbApplicator(dummyPDB);
			
			applicator.applyTo(program, program.getDataTypeManager(), program.getImageBase(), applicatorOptions,
					(MessageLog) null);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PdbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
}
