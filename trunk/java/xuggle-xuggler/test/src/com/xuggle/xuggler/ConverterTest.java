/*
 * Copyright (c) 2008, 2009 by Xuggle Incorporated.  All rights reserved.
 * 
 * This file is part of Xuggler.
 * 
 * You can redistribute Xuggler and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * Xuggler is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public
 * License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with Xuggler.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.xuggle.xuggler;

import com.xuggle.xuggler.Converter;
import com.xuggle.xuggler.IVideoResampler;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

public class ConverterTest extends TestCase
{
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private Converter converter=null;

  @Before
  public void setUp()
  {
    log.debug("Executing test case: {}", this.getName());
  }
  
  @Test
  public void testDefineOptions()
  {
    converter = new Converter();
    
    Options options = converter.defineOptions();
    
    assertTrue("no options", options != null);
  }
  
  @Test
  public void testParseOptions() throws ParseException
  {
    String[] args = new String[]{
        "--containerformat",
        "mov",
        "--acodec",
        "libmp3lame",
        "--asamplerate",
        "22050",
        "--achannels",
        "2",
        "--abitrate",
        "64000",
        "--aquality",
        "2",
        "--vcodec",
        "mpeg4",
        "--vscalefactor",
        "2.5",
        "--vbitrate",
        "300000",
        "--vquality",
        "2",
        "fixtures/testfile.flv",
        "out.flv"
    };
    converter = new Converter();
    
    Options options = converter.defineOptions();

    CommandLine cmdLine = converter.parseOptions(options, args);
    assertTrue("all commandline options successful", cmdLine != null);
  }
  
  @Test
  public void testInvalidOptions() throws ParseException
  {
    String[] args = new String[]{
        "--garbageoption",
        "fixtures/testfile.flv",
        "out.flv"
    };
    converter = new Converter();
    
    Options options = converter.defineOptions();
    
    try {
      converter.parseOptions(options, args);
      // if we get here, we've failed.
      fail("all commandline options successful");
    }
    catch (UnrecognizedOptionException exception)
    {
      // ignore this.
    }
    catch (Error e)
    {
      throw e;
    }
  }

  @Test
  public void testNoInputFile() throws ParseException
  {
    String[] args = new String[]{
        "--vcodec",
        "mpeg4"
    };
    converter = new Converter();
    
    Options options = converter.defineOptions();

    try {
      converter.parseOptions(options, args);
      // if we get here, we've failed.
      fail("all commandline options successful");
    }
    catch (ParseException exception)
    {
      // ignore this.
    }
    catch (Error e)
    {
      throw e;
    }
  
  }
  
  @Test
  public void testNoOutputFile() throws ParseException
  {
    String[] args = new String[]{
        "--vcodec",
        "mpeg4",
        "fixtures/testfile.flv"
    };
    converter = new Converter();
    
    Options options = converter.defineOptions();
  
    try {
      converter.parseOptions(options, args);
      // if we get here, we've failed.
      fail("all commandline options successful");
    }
    catch (ParseException exception)
    {
      // ignore this.
    }
    catch (Error e)
    {
      throw e;
    }
      
  }

  @Test
  public void testConversion() throws ParseException
  {
    // We do this to determine if this version of Xuggler can
    // support resampling
    boolean testResampling = IVideoResampler.isSupported(IVideoResampler.Feature.FEATURE_IMAGERESCALING);
    
    String[] args = new String[]{
        "--containerformat",
        "mov",
        "--acodec",
        "libmp3lame",
        "--asamplerate",
        "22050",
        "--achannels",
        "2",
        "--abitrate",
        "64000",
        "--aquality",
        "0",
        "--vcodec",
        "mpeg4",
        "--vscalefactor",
        testResampling ? "2.0" : "1.0",
        "--vbitrate",
        "300000",
        "--vbitratetolerance",
        "12000000",
        "--vquality",
        "0",
        "fixtures/testfile.flv",
        this.getClass().getName() + "_" + this.getName() + ".mov"
    };
    converter = new Converter();
    
    Options options = converter.defineOptions();

    CommandLine cmdLine = converter.parseOptions(options, args);
    assertTrue("all commandline options successful", cmdLine != null);
    
    converter.run(cmdLine);
  }

  @Test
  public void testConversionH264() throws ParseException
  {
    // We do this to determine if this version of Xuggler can
    // support resampling
    boolean testResampling = IVideoResampler.isSupported(IVideoResampler.Feature.FEATURE_IMAGERESCALING);
    
    String[] args = new String[]{
        "--containerformat",
        "mov",
        "--acodec",
        "libmp3lame",
        "--asamplerate",
        "22050",
        "--achannels",
        "2",
        "--abitrate",
        "64000",
        "--aquality",
        "0",
        "--vcodec",
        "libx264",
        "--vscalefactor",
        testResampling ? "2.0" : "1.0",
        "--vbitrate",
        "300000",
        "--vbitratetolerance",
        "12000000",
        "--vquality",
        "0",
        "fixtures/testfile_videoonly_20sec.flv",
        this.getClass().getName() + "_" + this.getName() + ".mp4"
    };
    
    // check if we have the libx264 encoder
    ICodec codec = ICodec.findEncodingCodecByName("libx264");
    if (codec == null)
      // we don't have libx264 so bail
      return;
    
    converter = new Converter();
    
    Options options = converter.defineOptions();

    CommandLine cmdLine = converter.parseOptions(options, args);
    assertTrue("all commandline options successful", cmdLine != null);
    
    converter.run(cmdLine);
  }

  @Test
  public void testConversionAac() throws ParseException
  {
    // We do this to determine if this version of Xuggler can
    // support resampling
    boolean testResampling = IVideoResampler.isSupported(IVideoResampler.Feature.FEATURE_IMAGERESCALING);
    // and if not, we skip this test because resampling is only supported in GPL
    // build, and now libfaac is only supported in GPL as well.
    if (!testResampling) return;
    
    String[] args = new String[]{
        "--containerformat",
        "mov",
        "--acodec",
        "libfaac",
        "--asamplerate",
        "22050",
        "--achannels",
        "2",
        "--abitrate",
        "64000",
        "--aquality",
        "0",
        "--vcodec",
        "mpeg4",
        "--vscalefactor",
        testResampling ? "2.0" : "1.0",
        "--vbitrate",
        "300000",
        "--vbitratetolerance",
        "12000000",
        "--vquality",
        "0",
        "fixtures/testfile_videoonly_20sec.flv",
        this.getClass().getName() + "_" + this.getName() + ".mov"
    };
    
    converter = new Converter();
    
    Options options = converter.defineOptions();

    CommandLine cmdLine = converter.parseOptions(options, args);
    assertTrue("all commandline options successful", cmdLine != null);
    
    converter.run(cmdLine);
  }


}
