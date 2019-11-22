IGA Preprocessor
# This project extracts the control data information from the json file generated from NURBS library.

APIs provided by nurbs-python (these APIs are deprecated; executables are available, on2json.exe and json2on.exe):
**Deprecated (included to give a brief view of NURBS APIs)
**Imports curves and surfaces from Rhinoceros/OpenNURBS .3dm files
  Parameters:	file_name (str) – input file name
1.	geomdl.exchange.import_3dm(file_name, **kwargs)


JSON Data from CAD Model:
1.	Control_points:
      a.	Points
      b.	Weights
2.	Degree_u
3.	Degree_v
4.	Dimension
5.	Knotvector_u
6.	Knotvector_v
7.	Rational
8.	Reversed
9.	Size_u
10.	Size_v
11.	Trims
      a.	Count
      b.	Data – many control_points included
          i.	Control_points
              1.	Points
          ii.	Degree
          iii.	Dimension
          iv.	Knotvector
          v.	Rational 
          vi.	Reversed
          vii.	Type
      
12.	Data structure for curve
●	- shape_type: "curve"   # should be the string "curve"
●	- dimension: (int)      # dimension of the curve; e.g. 2D or 3D (optional)
●	- degree: (int)         # degree of the curve
●	- knotvector: (list)    # knot vector
●	- control_points:
●	  - points: (list)      # Cartesian coordinates of the control points
●	  - weights: (list)     # weights vector (optional)
13.	Data structure for surface
●	- shape_type: "surface"   # should be the string "surface"
●	- dimension: 3            # dimension of the surface (optional) 
●	- degree_u: (int)         # degree (u-direction)
●	- degree_v: (int)         # degree (v-direction)
●	- knotvector_u: (list)    # knot vector (u-direction)
●	- knotvector_v: (list)    # knot vector (v-direction)
●	- size_u: (int)           # number of control points on the u-direction
●	- size_v: (int)           # number of control points on the v-direction
●	- control_points:
●	  - points: (list)        # Cartesian coordinates of the control points
●	  - weights: (list)       # weights vector (optional)
