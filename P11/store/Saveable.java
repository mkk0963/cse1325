package store;

import java.io.BufferedWriter;
import java.io.IOException;

interface Saveable
{
	public void save(BufferedWriter bw) throws IOException;
}
