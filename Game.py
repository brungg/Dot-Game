import turtle, random
import math

scale = 100

def cordAxis(t):
    t.up()
    t.goto(0, 1 * scale)
    t.down()
    t.setheading(0)
    t.right(90)
    t.fd(2 * scale)
    t.up()
    t.goto(-1 * scale, 0)
    t.down()
    t.left(90)
    t.fd(2 * scale)
    t.up()
    t.goto(0, 0)

def dot(t, r):
    t.up()
    while True:
        randx = 4 * (random.random() - (1/2))
        randy = 4 * (random.random() - (1/2))
 
        if math.sqrt((randx ** 2 + randy ** 2)) > 1.25 * r:
            break
    t.goto(randx * scale, randy * scale)
    t.down()
    t.dot((r * 2) * scale, "yellow")
    t.up()
    t.goto(0, 0)
    return (randx, randy), math.sqrt((randx ** 2 + randy ** 2))

def dotCheck(t, r, cord, dist, ang, chH, chM):
    ang = float(ang)
    if ang > 180:
        ang = ang - 360
    dist = float(dist)
    c = math.asin(r / dist)
    deg = 180/math.pi
    c = c * deg
    if cord[0] < 0.001 and cord[0] > -0.001:
        if cord[1] > 0:
            b = 90
        else:
            b = 270
    else:
        b = math.atan2(cord[1], cord[0])
        b = b * deg
    if ((b - c) <= ang) and ((b + c) >= ang):
        color = "green"
        chH += 1
    else:
        color = "red"
        chM += 1 
    t.up()
    t.goto(cord[0] * scale, cord[1] * scale)
    t.down()
    t.dot((r * 2) * scale, color)
    t.up()
    t.goto(0, 0)
    return chH, chM

def line(t, ang, dist):
    t.setheading(0)
    t.left(float(ang))
    t.down()
    t.fd(dist * scale)
    t.up()
    t.goto(0, 0)
    t.setheading(0)
    
def main():
    rad = float(input("Enter taget radius: "))
    t = turtle
    chH = 0
    chM = 0
    cordAxis(t)
    a = ""
    while(True):
        cord = dot(t, rad)
        print("Hits:", chH, "\tMisses:", chM)
        a = input("Enter angle of trajectory (deg), (x to exit): ")
        if a != "x":
            line(t, a, cord[1])
            c = dotCheck(t, rad, cord[0], cord[1], a, chH, chM)
            chH = c[0]
            chM = c[1]
        else:
            break

if __name__ == "__main__":
    main()
