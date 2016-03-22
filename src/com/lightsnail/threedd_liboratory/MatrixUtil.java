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
				1,0,0,//0 ��1��2��
				0,1,0,//3 ��4��5��
				0,0,1,//6 ��7��8��
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

	public static float[] mul4(float[]... mMatrixS ) {
		float[] finalMatrx = mMatrixS[0];
		for (int i = 1; i < mMatrixS.length; i++) {
			finalMatrx = mul4(finalMatrx,mMatrixS[i]); 
		}
		return finalMatrx;
	}
	public static float[] mul4(float[]  mMatrixLeft, float[]  mMatrixRight ) {
		float[] fs = new float[]{
				1,0,0,0,//0 1 2 3
				0,1,0,0,//4 5 6 7
				0,0,1,0,//8 9 10 11
				0,0,0,1,//12 13 14 15
		};
		//
		fs[0] = 
				mMatrixLeft[0]  * mMatrixRight[0] + 
				mMatrixLeft[1]* mMatrixRight[4] + 
				mMatrixLeft[2] * mMatrixRight[8] + 
				mMatrixLeft[3] * mMatrixRight[10] ;
		fs[1] = 
				mMatrixLeft[0]  * mMatrixRight[1] + 
				mMatrixLeft[1]  * mMatrixRight[5] + 
				mMatrixLeft[2]  * mMatrixRight[9] +
				mMatrixLeft[3]  * mMatrixRight[13] ;
		fs[2] = 
				mMatrixLeft[0]  * mMatrixRight[2] + 
				mMatrixLeft[1]  * mMatrixRight[6] + 
				mMatrixLeft[2]  * mMatrixRight[10] +
				mMatrixLeft[3]  * mMatrixRight[14] ;
		fs[3] = 
				mMatrixLeft[0]  * mMatrixRight[3] + 
				mMatrixLeft[1]  * mMatrixRight[7] + 
				mMatrixLeft[2]  * mMatrixRight[11] +
				mMatrixLeft[3]  * mMatrixRight[15] ;
		//
		fs[4] = 
				mMatrixLeft[4]  * mMatrixRight[0] + 
				mMatrixLeft[5]* mMatrixRight[4] + 
				mMatrixLeft[6] * mMatrixRight[8] + 
				mMatrixLeft[7] * mMatrixRight[10] ;
		fs[5] = 
				mMatrixLeft[4]  * mMatrixRight[1] + 
				mMatrixLeft[5]  * mMatrixRight[5] + 
				mMatrixLeft[6]  * mMatrixRight[9] +
				mMatrixLeft[7]  * mMatrixRight[13] ;
		fs[6] = 
				mMatrixLeft[4]  * mMatrixRight[2] + 
				mMatrixLeft[5]  * mMatrixRight[6] + 
				mMatrixLeft[6]  * mMatrixRight[10] +
				mMatrixLeft[7]  * mMatrixRight[14] ;
		fs[7] = 
				mMatrixLeft[4]  * mMatrixRight[3] + 
				mMatrixLeft[5]  * mMatrixRight[7] + 
				mMatrixLeft[6]  * mMatrixRight[11] +
				mMatrixLeft[7]  * mMatrixRight[15] ;
		//

		fs[8] = 
				mMatrixLeft[8]  * mMatrixRight[0] + 
				mMatrixLeft[9]* mMatrixRight[4] + 
				mMatrixLeft[10] * mMatrixRight[8] + 
				mMatrixLeft[11] * mMatrixRight[10] ;
		fs[9] = 
				mMatrixLeft[8]  * mMatrixRight[1] + 
				mMatrixLeft[9]  * mMatrixRight[5] + 
				mMatrixLeft[10]  * mMatrixRight[9] +
				mMatrixLeft[11]  * mMatrixRight[13] ;
		fs[10] = 
				mMatrixLeft[8]  * mMatrixRight[2] + 
				mMatrixLeft[9]  * mMatrixRight[6] + 
				mMatrixLeft[10]  * mMatrixRight[10] +
				mMatrixLeft[11]  * mMatrixRight[14] ;
		fs[11] = 
				mMatrixLeft[8]  * mMatrixRight[3] + 
				mMatrixLeft[9]  * mMatrixRight[7] + 
				mMatrixLeft[10]  * mMatrixRight[11] +
				mMatrixLeft[11]  * mMatrixRight[15] ;
		////

		fs[12] = 
				mMatrixLeft[12]  * mMatrixRight[0] + 
				mMatrixLeft[13]* mMatrixRight[4] + 
				mMatrixLeft[14] * mMatrixRight[8] + 
				mMatrixLeft[15] * mMatrixRight[10] ;
		fs[13] = 
				mMatrixLeft[12]  * mMatrixRight[1] + 
				mMatrixLeft[13]  * mMatrixRight[5] + 
				mMatrixLeft[14]  * mMatrixRight[9] +
				mMatrixLeft[15]  * mMatrixRight[13] ;
		fs[14] = 
				mMatrixLeft[12]  * mMatrixRight[2] + 
				mMatrixLeft[13]  * mMatrixRight[6] + 
				mMatrixLeft[14]  * mMatrixRight[10] +
				mMatrixLeft[15]  * mMatrixRight[14] ;
		fs[15] = 
				mMatrixLeft[12]  * mMatrixRight[3] + 
				mMatrixLeft[13]  * mMatrixRight[7] + 
				mMatrixLeft[14]  * mMatrixRight[11] +
				mMatrixLeft[15]  * mMatrixRight[15] ;
		return fs;
	}

	public static QC_PointF mul4(QC_PointF qc_PointF, float[] targetMatrix) {
		QC_PointF 	rQc = new QC_PointF();
		rQc.x = 
				qc_PointF.x * targetMatrix[0] + 
				qc_PointF.y * targetMatrix[4] + 
				qc_PointF.z * targetMatrix[8] + 
				qc_PointF.s * targetMatrix[10] ;
		rQc.y = 
				qc_PointF.x  * targetMatrix[1] + 
				qc_PointF.y * targetMatrix[5] + 
				qc_PointF.z * targetMatrix[9] +
				qc_PointF.s * targetMatrix[13] ;
		rQc.z = 
				qc_PointF.x  * targetMatrix[2] + 
				qc_PointF.y  * targetMatrix[6] + 
				qc_PointF.z   * targetMatrix[10] +
				qc_PointF.s   * targetMatrix[14] ;
		rQc.s = 
				qc_PointF.x * targetMatrix[3] + 
				qc_PointF.y * targetMatrix[7] + 
				qc_PointF.z  * targetMatrix[11] +
				qc_PointF.s * targetMatrix[15] ;
		rQc.x /= rQc.s;
		rQc.y /= rQc.s;
		rQc.z /= rQc.s;
		rQc.s /= rQc.s;
		return rQc;
	}

}
