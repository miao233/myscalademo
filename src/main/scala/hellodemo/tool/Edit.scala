package hellodemo.tool

import breeze.linalg.DenseMatrix

/**
 * Author: gocn
 * 2022/9/8 16:24
 * Desc: ${DESCRIPTION}
 * History:
 * <time> <desc>
 * */
object Edit {
  def editDist(s1:String, s2:String):Int ={
    val s1_length = s1.length+1
    val s2_length = s2.length+1

    val matrix = DenseMatrix.zeros[Int](s1_length, s2_length)
    for(i <- 1.until(s1_length)){
      matrix(i,0) = matrix(i-1, 0) + 1
    }

    for(j <- 1.until(s2_length)){
      matrix(0,j) = matrix(0, j-1) + 1
    }

    var cost = 0
    for(j <- 1.until(s2_length)){
      for(i <- 1.until(s1_length)){
        if(s1.charAt(i-1)==s2.charAt(j-1)){
          cost = 0
        }else{
          cost = 1
        }
        matrix(i,j)=math.min(math.min(matrix(i-1,j)+1,matrix(i,j-1)+1),matrix(i-1,j-1)+cost)
      }
    }
    matrix(s1_length-1,s2_length-1)
  }
}
