package com.example.irattobar;


public class Vec {
	
	public float _x,_y;
	
	Vec(){
		_x = _y = 0;
	}
	
	Vec(float x,float y){
		_x=x;
		_y=y;
	}
	
	//�p�x���擾����
	float getAngle(){
		return (float)Math.atan2(_y, _x);
	}
	
	//�傫�����擾����
	float getLength(){
		return (float)Math.sqrt(_x*_x+_y*_y);
	}
	
	//����������
	//�����̒l���傫�����傫����Έ����̒l�ɂ���B
	void setLengthCap(float maxLength){
		float len = getLength();
		if(maxLength==0){
			return;
		}
		if(len > maxLength){
			float rate = len/maxLength;
			_x /= rate;
			_y /= rate;
		}
	}
	
	//vec�����̌�����rate���قǃu�����h����
	void blend(Vec vec , float rate){
		float w = vec._x - _x;
		float h = vec._y - _y;
		_x += w*rate;
		_y += h*rate;
	}
}
