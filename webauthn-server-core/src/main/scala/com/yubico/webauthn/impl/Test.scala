package com.yubico.webauthn.impl

import java.security.MessageDigest

import com.yubico.u2f.data.messages.key.util.U2fB64Encoding
import com.yubico.webauthn.data.ArrayBuffer
import com.yubico.webauthn.data.AttestationObject
import com.yubico.webauthn.data.AuthenticatorData
import com.yubico.webauthn.util.WebAuthnCodecs


object Test extends App {

  // val attestationObject: ArrayBuffer = U2fB64Encoding.decode("o2NmbXRmcGFja2VkaGF1dGhEYXRhWLhsce9f2O4QMCXrg1cu1lwknOxtXBryURzsRWDk8tD7pkEAAAAAAAAAAAAAAAAAAAAAAAAAAABAawIjwBjPgvsbJe-gqVwMFEQeZ0zTgj93jw3fFdOTLTshl3F2qwb6O35qI520Iw53fXcsNMoFWL767oiSpHB4ggQ0PVe2C-FLegMiA73oT8Tbd-R7wB7HOrYY5FOQdmCN2aGm5dT2RrmsRHq_EhEUF6L_X4aY2zIkXH7-UlI0MtQMZ2F0dFN0bXSjY2FsZ2VFUzI1NmNzaWdYRzBFAiBgPH9xOEVrf3XqFbYkn78oHbBu-c8-0z0g6sT00MzcJAIhAJdwAJuhzS_SqJm8q8R--yc_YXj4VvNLlCVWnFlycIIXY3g1Y4FZAlMwggJPMIIBN6ADAgECAgQSNtF_MA0GCSqGSIb3DQEBCwUAMC4xLDAqBgNVBAMTI1l1YmljbyBVMkYgUm9vdCBDQSBTZXJpYWwgNDU3MjAwNjMxMCAXDTE0MDgwMTAwMDAwMFoYDzIwNTAwOTA0MDAwMDAwWjAxMS8wLQYDVQQDDCZZdWJpY28gVTJGIEVFIFNlcmlhbCAyMzkyNTczNDEwMzI0MTA4NzBZMBMGByqGSM49AgEGCCqGSM49AwEHA0IABNNlqR5emeDVtDnA2a-7h_QFjkfdErFE7bFNKzP401wVE-QNefD5maviNnGVk4HJ3CsHhYuCrGNHYgTM9zTWriGjOzA5MCIGCSsGAQQBgsQKAgQVMS4zLjYuMS40LjEuNDE0ODIuMS41MBMGCysGAQQBguUcAgEBBAQDAgUgMA0GCSqGSIb3DQEBCwUAA4IBAQAiG5uzsnIk8T6-oyLwNR6vRklmo29yaYV8jiP55QW1UnXdTkEiPn8mEQkUac-Sn6UmPmzHdoGySG2q9B-xz6voVQjxP2dQ9sgbKd5gG15yCLv6ZHblZKkdfWSrUkrQTrtaziGLFSbxcfh83vUjmOhDLFC5vxV4GXq2674yq9F2kzg4nCS4yXrO4_G8YWR2yvQvE2ffKSjQJlXGO5080Ktptplv5XN4i5lS-AKrT5QRVbEJ3B4g7G0lQhdYV-6r4ZtHil8mF4YNMZ0-RaYPxAaYNWkFYdzOZCaIdQbXRZefgGfbMUiAC2gwWN7fiPHV9eu82NYypGU32OijG9BjhGt_").toVector
  val attestationObject: ArrayBuffer = U2fB64Encoding.decode("o2hhdXRoRGF0YVjKbHHvX9juEDAl64NXLtZcJJzsbVwa8lEc7EVg5PLQ-6ZBAAAAAAAAAAAAAAAAAAAAAAAAAAAAQOiUTsFEO0Ng5k3_k2diNEWJw8PDfXUMm9dPQ6piFAgN2ZqYXc2edf-nm9qYcznSHZ7My05HRWC8b15UdtpNYHWjY2FsZ2VFUzI1NmF4WCDS7Esl2DRo9RpWifrwLuAwCx-x5JN5Vl5RNla0xeBf0mF5WCDXSTYlDGlsKid4rRm6wi6NY5FDLiCOXJQpzkC8l2guKGNmbXRoZmlkby11MmZnYXR0U3RtdKJjeDVjgVkCUzCCAk8wggE3oAMCAQICBCrZavMwDQYJKoZIhvcNAQELBQAwLjEsMCoGA1UEAxMjWXViaWNvIFUyRiBSb290IENBIFNlcmlhbCA0NTcyMDA2MzEwIBcNMTQwODAxMDAwMDAwWhgPMjA1MDA5MDQwMDAwMDBaMDExLzAtBgNVBAMMJll1YmljbyBVMkYgRUUgU2VyaWFsIDIzOTI1NzM0NTE2NTUwMzg3MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEL-GiPr-lWz5GHVmkNSLXl0iYHLptKJqY8b19_2VmgNu77bwrrmB-bvdy9XawTVTE5fMvWW8m5hEVxycs9sp1lKM7MDkwIgYJKwYBBAGCxAoCBBUxLjMuNi4xLjQuMS40MTQ4Mi4xLjIwEwYLKwYBBAGC5RwCAQEEBAMCBDAwDQYJKoZIhvcNAQELBQADggEBAIVq-ovPTz9iXykbwRWOPH69JVK891cHU_USHaalTSTMz64nztarMRKMKX5bW4kF3aAgF5MfH19ZJZNZUfwAS8viCt19jQUvlUOzSWwVuDEOEMvZuwU4J09YPq0fRRKIw-p20HCtROU6_qjyLR9zYl_y1Yn-MN8mYst8u3yZYYCtz6mKTQEs8xNGzRF0alhI6L7t8-MMy9nB3SIWcbKDiGH2WkU2I7UY1VZ_qPCjzhBd9PE5U-EU6lngp_L-ZohnQy5S_WovZPc8SM2bOPLfuix6SzsRKN8m1mok-JXdoLYRgPQUT2twdcMYpJrgi1jTatseMFNnKxfFoZ9_CiLxDpRjc2lnWEcwRQIhAOdPkMJhSa5IQ8nOA4BYUjYMA6d5WGEA3sDuEBnkXxm6AiAJ_vfKhJdC5WGcMxZfgnz4I9JJ_-D-VHSHljEvDyUS7w").toVector

  println(WebAuthnCodecs.cbor.readTree(attestationObject.toArray))

  val attObj = WebAuthnCodecs.cbor.readTree(attestationObject.toArray)

  println(attObj.get("authData").getNodeType)
  println(attObj.get("fmt").getNodeType)
  println(attObj.get("attStmt").getNodeType)

  val attStmt = attObj.get("attStmt")

  println(attStmt)
  println(attStmt.fieldNames)

  println(attStmt.get("x5c").getNodeType)
  println(attStmt.get("x5c"))

  println(attStmt.get("x5c").get(0).getNodeType)
  println(attStmt.get("x5c").get(0).isBinary)
  println(attStmt.get("x5c").get(0))


  val parsedAttObj = AttestationObject(attestationObject)
  println(parsedAttObj)
  println(parsedAttObj.authenticatorData)
  println(parsedAttObj.authenticatorData.attestationData)
  println(parsedAttObj.attestationStatement)
  println(parsedAttObj.attestationStatement.getNodeType)

}