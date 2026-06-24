import zipfile
import xml.etree.ElementTree as ET
import os

def read_docx_text(filepath):
    if not os.path.exists(filepath):
        print(f"File not found: {filepath}")
        return ""
    try:
        with zipfile.ZipFile(filepath) as docx:
            xml_content = docx.read('word/document.xml')
            root = ET.fromstring(xml_content)
            
            # The namespace for Word XML
            ns = {'w': 'http://schemas.openxmlformats.org/wordprocessingml/2006/main'}
            
            # Find all text elements
            texts = []
            for p in root.findall('.//w:p', ns):
                p_text = []
                for r in p.findall('.//w:r', ns):
                    t = r.find('.//w:t', ns)
                    if t is not None and t.text:
                        p_text.append(t.text)
                if p_text:
                    texts.append(''.join(p_text))
            return '\n'.join(texts)
    except Exception as e:
        print(f"Error reading {filepath}: {e}")
        return ""

# Inspect documents
dp_path = "Digital-Nurture-JavaFSE-main/Java FSE/Deepskilling/Engineering concepts/Design Patterns and Principles.docx"
dsa_path = "Digital-Nurture-JavaFSE-main/Java FSE/Deepskilling/Engineering concepts/Algorithms_Data Structures.docx"
plsql_path = "Digital-Nurture-JavaFSE-main/Java FSE/Deepskilling/PL SQL programming/PLSQL_Exercises.docx"

print("=== DESIGN PATTERNS DOCX CONTENT ===")
dp_text = read_docx_text(dp_path)
print(dp_text[:1500])
print("\n" + "="*40 + "\n")

print("=== DSA DOCX CONTENT ===")
dsa_text = read_docx_text(dsa_path)
print(dsa_text[:1500])
print("\n" + "="*40 + "\n")

print("=== PLSQL DOCX CONTENT ===")
plsql_text = read_docx_text(plsql_path)
print(plsql_text[:1500])
