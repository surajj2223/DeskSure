# DeskSure
Two Factor Authentication System using AES (Advanced Encryption Standard) and mobile bluetooth
------------------------------------------------------------------------------------------------
DeskSure is one of my college time projects and is essentially a desktop solution to prevent unauthorized access to computer 
files using 128-bit AES algorithm and Bluetooth device. The utility starts along side of booting of computer and stays awake
as a background service keeping selected files encrypted using AES and monitoring any unauthorized access attempt to secured
files by ensuring Bluetooth token (an OTP) from an authenticated Bluetooth device, if fails maintains a respective log for 
access failure and prevents decryption of data.

# Technology Description:
1. Java Swing for User Interaction.
2. Bluecove 2.0.1 API for managing Bluetooth Stack. (JSR-82 Implementation)
3. 128-bit AES Standard Algorithm for encryption and decryption of files.
4. Singleton design pattern is used for managing user data throughout the application lifecycle which reduces file IO 
    operations as the system doesnt have dedicated structured database for saving user data and preference.
5. User Data Saving Scheme: All user data is saved in the application itself in serialized files.

# Dependencies:
1. Bluecove-2.1.0 (jar)
2. Bluecove-2.1.1-SNAPSHOT (jar)
3. jCalendar-1.4

# Pending implementation details.
1. Not yet configured to run at booting.
2. Global data access module is under implementation (Not fully developed).
