package com.example.tuner

import android.content.Context

class DatabaseFixture
{
    fun createBasicDataInDB(context: Context) {
//        DbInstrumentHandler(context).drop()
        if (DbInstrumentHandler(context).getAll().isNotEmpty()) {
            return
        }
//        Create data in Instrument table
        var instrument = InstrumentModel(1, InstrumentEnum.GUITAR);
        val handler = DbInstrumentHandler(context)
        DbInstrumentHandler(context).create()
        handler.insertData(instrument, true)

        instrument = InstrumentModel(2, InstrumentEnum.BASS);
        handler.insertData(instrument, true)


//        Create data in Tune table
        var currNumber = 0;
        val letters = arrayOf("C", "D", "E", "F", "G", "A", "B")
        val frequencies = arrayOf(16.35,17.32,18.35,19.45,20.60,21.83,23.12,24.50,25.96,27.50,29.14,30.87,32.70,34.65,36.71,38.89,41.20,43.65,46.25,49.00,51.91,55.00,58.27,61.74,65.41,69.30,73.42,77.78,82.41,87.31,92.50,98.00,103.83,110.00,116.54,123.47,130.81,138.59,146.83,155.56,164.81,174.61,185.00,196.00,207.65,220.00,233.08,246.94,261.63,277.18,293.66,311.13,329.63,349.23,369.99,392.00,415.30,440.00,466.16,493.88,523.25,554.37,587.33,622.25,659.25,698.46,739.99,783.99,830.61,880.00,932.33,987.77,1046.50,1108.73,1174.66,1244.51,1318.51,1396.91,1479.98,1567.98,1661.22,1760.00,1864.66,1975.53,2093.00,2217.46,2349.32,2489.02,2637.02,2793.83,2959.96,3135.96,3322.44,3520.00,3729.31,3951.07,4186.01,4434.92,4698.63,4978.03,5274.04,5587.65,5919.91,6271.93,6644.88,7040.00,7458.62,7902.13)

        val handlerTune = DbTuneHandler(context)
        handlerTune.create()

        var note = ""
        var tune: TuneModel
        var iterator = 0;

        while (currNumber < 9) {
            for (letter in letters) {
                note = "$letter$currNumber"
                tune = TuneModel(++iterator, note, frequencies[iterator-1])

                handlerTune.insertData(tune, true)

                if (letter !== "B" && letter !== "E") {
                    note = "$letter#$currNumber"
                    tune = TuneModel(++iterator, note, frequencies[iterator-1])
                    handlerTune.insertData(tune, true)
                }
            }
            currNumber++
        }

//        Create data in BasicTuner table
        val handler2 = DbBasicTunerHandler(context)
        handler2.create()

        var basicTuner = BasicTunerModel(1, "E-standard", 1, false, 10, TuneEnum.E2, TuneEnum.A2,TuneEnum.D3,TuneEnum.G3,TuneEnum.B3,TuneEnum.E4, null, null, null, null);
        handler2.insertData(basicTuner, true)

        basicTuner = BasicTunerModel(2, "Drop D", 1, false, 9, TuneEnum.D2, TuneEnum.A2,TuneEnum.D3,TuneEnum.G3,TuneEnum.B3,TuneEnum.E4, null, null, null, null);
        handler2.insertData(basicTuner, true)

        basicTuner = BasicTunerModel(3, "4-string standard", 2, false, 8, TuneEnum.E1, TuneEnum.A1,TuneEnum.D1,TuneEnum.G2,null, null, null, null, null, null);
        handler2.insertData(basicTuner, true)

        basicTuner = BasicTunerModel(4, "Drop D 4-string", 2,  false,7, TuneEnum.D1, TuneEnum.A1,TuneEnum.D1,TuneEnum.G2, null, null, null, null, null, null);
        handler2.insertData(basicTuner, true)

        basicTuner = BasicTunerModel(5, "Drop A 5-string", 2,  false,6, TuneEnum.A1, TuneEnum.E1, TuneEnum.A1,TuneEnum.D1,TuneEnum.G2, null, null, null, null, null);
        handler2.insertData(basicTuner, true)
    }
}