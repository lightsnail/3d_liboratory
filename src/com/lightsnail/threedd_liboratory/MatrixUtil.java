package com.lightsnail.threedd_liboratory;

import com.lightsnail.threedd_world.QC_PointF;

public class MatrixUtil {

	public static QC_PointF mul3(QC_PointF qc_PointF, float[] mMatrixTran) {
		QC_PointF 	rQc = new QC_PointF();
		rQc.x = 
				qc_PointF.x * mMatrixTran[0] + 
				qc_PointF.y * mMatrixTran[3] + 
				qc_PointF.s * mMatrixTran[6] ;
		rQc.y = 
				qc_PointF.x * mMatrixTran[1] + 
				qc_PointF.y * mMatrixTran[4] + 
				qc_PointF.s * mMatrixTran[7] ;
		rQc.s = 
				qc_PointF.x * mMatrixTran[2] + 
				qc_PointF.y * mMatrixTran[5] + 
				qc_PointF.s * mMatrixTran[8] ;
		return rQc;
	}

	public static float[] mul3(float[] mMatrixLeft, float[] mMatrixRight) {
		float[] fs = new float[]{
				1,0,0,//0 £¬1£¬2£¬
				0,1,0,//3 £¬4£¬5£¬
				0,0,1,//6 £¬7£¬8£¬
		};
		fs[0] = 
				mMatrixLeft[0]  * mMatrixRight[0] + 
				mMatrixLeft[1]* mMatrixRight[3] + 
				mMatrixLeft[2] * mMatrixRight[6] ;
		fs[1] = 
				mMatrixLeft[0]  * mMatrixRight[1] + 
				mMatrixLeft[1]  * mMatrixRight[4] + 
				mMatrixLeft[2]  * mMatrixRight[7] ;
		fs[2] = 
				mMatrixLeft[0]  * mMatrixRight[2] + 
				mMatrixLeft[1]  * mMatrixRight[5] + 
				mMatrixLeft[2] * mMatrixRight[8] ;

		fs[3] = 
				mMatrixLeft[3]  * mMatrixRight[0] + 
				mMatrixLeft[4]* mMatrixRight[3] + 
				mMatrixLeft[5] * mMatrixRight[6] ;
		fs[4] = 
				mMatrixLeft[3]  * mMatrixRight[1] + 
				mMatrixLeft[4]  * mMatrixRight[4] + 
				mMatrixLeft[5]  * mMatrixRight[7] ;
		fs[5] = 
				mMatrixLeft[3]  * mMatrixRight[2] + 
				mMatrixLeft[4]  * mMatrixRight[5] + 
				mMatrixLeft[5] * mMatrixRight[8] ;
		

		fs[6] = 
				mMatrixLeft[6]  * mMatrixRight[0] + 
				mMatrixLeft[7]* mMatrixRight[3] + 
				mMatrixLeft[8] * mMatrixRight[6] ;
		fs[7] = 
				mMatrixLeft[6]  * mMatrixRight[1] + 
				mMatrixLeft[7]  * mMatrixRight[4] + 
				mMatrixLeft[8]  * mMatrixRight[7] ;
		fs[8] = 
				mMatrixLeft[6]  * mMatrixRight[2] + 
				mMatrixLeft[7]  * mMatrixRight[5] + 
				mMatrixLeft[8] * mMatrixRight[8] ;
		
		return fs;
	}

}
