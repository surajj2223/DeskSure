# DeskSure
Two Factor Authentication System using AES (Advanced Encryption Standard) and mobile bluetooth
-----------------------------------------------------------------------------------------------------
DeskSure is a desktop solution to prevent unauthorized access to computer files using 128-bit AES algorithm and Bluetooth device.
The utility starts along side of booting of computer and stays awake as a background service keeping selected files encrypted using
AES and monitoring any unauthorized access attempt to secured files by ensuring Bluetooth token (an OTP) from an authenticated
Bluetooth device, if fails maintains a respective log for access failure and prevents decryption of data.

Technology Description:
# Java Swing for User Interaction.
# Bluecove 2.0.1 API for managing Bluetooth Stack.
# 128-bit AES Standard Algorithm for encryption and decryption of data.
