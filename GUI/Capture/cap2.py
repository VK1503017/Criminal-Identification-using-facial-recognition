import cv2
import os
import sys

cam = cv2.VideoCapture(0)

cv2.namedWindow("test")

img_counter = 0
count=0
while True:
    ret, frame = cam.read()
    cv2.imshow("test", frame)
    if not ret:
        break
    k = cv2.waitKey(1)

    if k%256 == 27:
        # ESC pressed
        print("Escape hit, closing...")
        break
    elif k%256 == 32:
        # SPACE pressed
        #cv2.imwrite(img_name, frame)
        path=''+sys.argv[1]
        cv2.imwrite(os.path.join(path,str(img_counter)+'.jpg'),frame);
        print("{} written!",path+str(img_counter)+".jpg")
        img_counter += 1
        if count==20:
        	break
        count=count+1

cam.release()

cv2.destroyAllWindows()