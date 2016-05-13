package com.omstu.cursorAnalyzer.repository;

import java.util.ArrayList;

public class MetricsRepository {
    private byte[] DBBytes;
//    private XmlDocument xmlDocument;
//    private XmlNode root;
    private String username;

    public void saveMouseParamsAndMetrics(
            ArrayList<Double> midDiffTracks, ArrayList<Double> maxDiffTracks, ArrayList<Double> T,
            ArrayList<Float[]> ampContainer, ArrayList<Double> mouseSpeed, ArrayList<Double> energyContainer,
            ArrayList<Double> distanceLen) {

//        byte[] byteStream;
//
//        using (FileStream fileStream = new FileStream(fileName, FileMode.OpenOrCreate, FileAccess.Read))
//        {
//            byteStream = new byte[fileStream.Length];
//            fileStream.Read(byteStream, 0, byteStream.Length);
//        }
//        using (MemoryStream memoryStream = new MemoryStream(byteStream))
//        {
//            xmlDocument = new XmlDocument();
//            xmlDocument.Load(memoryStream);
//        }
//
//        root = xmlDocument.DocumentElement;
//        XmlNode featuresNode = root.SelectSingleNode("descendant::Features");
//
//        XmlNode classNode = xmlDocument.CreateElement("Class");
//        XmlAttribute nameAttribute = xmlDocument.CreateAttribute("name");
//
//        XmlNode realizationNode;
//        XmlNode featureNode;
//        XmlAttribute idAttribute;
//        XmlAttribute valueAttribute;
//
//        nameAttribute.InnerText = userName;
//        classNode.Attributes.Append(nameAttribute);
//
//        for (int i = 0; i < midDiffTracks.Count; i++) {
//            realizationNode = xmlDocument.CreateElement("Realization");
//
//            featureNode = xmlDocument.CreateElement("Feature");
//            idAttribute = xmlDocument.CreateAttribute("id");
//            idAttribute.InnerText = "1";
//            valueAttribute = xmlDocument.CreateAttribute("value");
//            valueAttribute.InnerText = midDiffTracks[i].ToString();
//            featureNode.Attributes.Append(idAttribute);
//            featureNode.Attributes.Append(valueAttribute);
//            realizationNode.AppendChild(featureNode);
//
//            featureNode = xmlDocument.CreateElement("Feature");
//            idAttribute = xmlDocument.CreateAttribute("id");
//            idAttribute.InnerText = "2";
//            valueAttribute = xmlDocument.CreateAttribute("value");
//            valueAttribute.InnerText = maxDiffTracks[i].ToString();
//            featureNode.Attributes.Append(idAttribute);
//            featureNode.Attributes.Append(valueAttribute);
//            realizationNode.AppendChild(featureNode);
//
//            featureNode = xmlDocument.CreateElement("Feature");
//            idAttribute = xmlDocument.CreateAttribute("id");
//            idAttribute.InnerText = "3";
//            valueAttribute = xmlDocument.CreateAttribute("value");
//            valueAttribute.InnerText = T[i].ToString();
//            featureNode.Attributes.Append(idAttribute);
//            featureNode.Attributes.Append(valueAttribute);
//            realizationNode.AppendChild(featureNode);
//
//            for (int j = 0; j < 10; j++) {
//                featureNode = xmlDocument.CreateElement("Feature");
//                idAttribute = xmlDocument.CreateAttribute("id");
//                idAttribute.InnerText = (j + 4).ToString();
//                valueAttribute = xmlDocument.CreateAttribute("value");
//                valueAttribute.InnerText = ampContainer[i][j].ToString();
//                featureNode.Attributes.Append(idAttribute);
//                featureNode.Attributes.Append(valueAttribute);
//                realizationNode.AppendChild(featureNode);
//            }
//
//            featureNode = xmlDocument.CreateElement("Feature");
//            idAttribute = xmlDocument.CreateAttribute("id");
//            idAttribute.InnerText = "14";
//            valueAttribute = xmlDocument.CreateAttribute("value");
//            valueAttribute.InnerText = mouseSpeed[i].ToString();
//            featureNode.Attributes.Append(idAttribute);
//            featureNode.Attributes.Append(valueAttribute);
//            realizationNode.AppendChild(featureNode);
//
//            featureNode = xmlDocument.CreateElement("Feature");
//            idAttribute = xmlDocument.CreateAttribute("id");
//            idAttribute.InnerText = "15";
//            valueAttribute = xmlDocument.CreateAttribute("value");
//            valueAttribute.InnerText = energyContainer[i].ToString();
//            featureNode.Attributes.Append(idAttribute);
//            featureNode.Attributes.Append(valueAttribute);
//            realizationNode.AppendChild(featureNode);
//
//            featureNode = xmlDocument.CreateElement("Feature");
//            idAttribute = xmlDocument.CreateAttribute("id");
//            idAttribute.InnerText = "16";
//            valueAttribute = xmlDocument.CreateAttribute("value");
//            valueAttribute.InnerText = distanceLen[i].ToString();
//            featureNode.Attributes.Append(idAttribute);
//            featureNode.Attributes.Append(valueAttribute);
//            realizationNode.AppendChild(featureNode);
//
//            classNode.AppendChild(realizationNode);
//        }
//
//        featuresNode.AppendChild(classNode);
//
//        MemoryStream memoryStream = new MemoryStream() {
//            xmlDocument.Save(memoryStream);
//            DBBytes = new byte[(int)memoryStream.Length];
//            DBBytes = memoryStream.ToArray();
//        }
//
//        FileStream fileStream = new FileStream(fileName, FileMode.Open, FileAccess.Write) {
//            fileStream.Write(DBBytes, 0, DBBytes.Length);
//        }
    }
}
