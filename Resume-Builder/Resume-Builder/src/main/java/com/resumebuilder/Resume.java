// Placeholder for Resume.java

public class Resume {
    private String fullName;
    private String email;
    private String phone;
    private String summary;
    private String education;
    private String experience;
    private String skills;

    public Resume() {}

    // Getters and setters
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

    public String getEducation() { return education; }
    public void setEducation(String education) { this.education = education; }

    public String getExperience() { return experience; }
    public void setExperience(String experience) { this.experience = experience; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    // Return a basic formatted (plain-text) resume
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (fullName != null && !fullName.isEmpty()) sb.append(fullName).append("\n");
        if ((email != null && !email.isEmpty()) || (phone != null && !phone.isEmpty())) {
            if (email != null && !email.isEmpty()) sb.append(email);
            if (phone != null && !phone.isEmpty()) sb.append(" | ").append(phone);
            sb.append("\n\n");
        }

        if (summary != null && !summary.isEmpty()) {
            sb.append("Professional Summary\n");
            sb.append("--------------------\n");
            sb.append(summary).append("\n\n");
        }

        if (experience != null && !experience.isEmpty()) {
            sb.append("Experience\n");
            sb.append("----------\n");
            sb.append(experience).append("\n\n");
        }

        if (education != null && !education.isEmpty()) {
            sb.append("Education\n");
            sb.append("---------\n");
            sb.append(education).append("\n\n");
        }

        if (skills != null && !skills.isEmpty()) {
            sb.append("Skills\n");
            sb.append("------\n");
            sb.append(skills).append("\n");
        }

        return sb.toString().trim();
    }
}
