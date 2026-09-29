package com.pawsitive.petacademy.model

import com.pawsitive.petacademy.R

data class Course(
    val id: String,
    val name: String,
    val fee: Int,
    val duration: String,
    val includes: List<String>,
    val imageRes: Int
)

object CourseRepository {

    val courses = listOf(
        Course(
            id = "canine-obedience",
            name = "Canine Obedience Training",
            fee = 1500,
            duration = "6-month programme",
            includes = listOf(
                "Basic commands",
                "Leash training",
                "Behaviour correction",
                "Socialisation"
            ),
            imageRes = R.drawable.img_canine_obedience
        ),
        Course(
            id = "pet-grooming",
            name = "Pet Grooming",
            fee = 1500,
            duration = "6-month programme",
            includes = listOf(
                "Bathing and drying",
                "Coat and skin care",
                "Nail and paw care",
                "Breed cuts and styling"
            ),
            imageRes = R.drawable.img_pet_grooming
        ),
        Course(
            id = "animal-behaviour",
            name = "Animal Behaviour",
            fee = 1500,
            duration = "6-month programme",
            includes = listOf(
                "Reading body language",
                "Common behaviour problems",
                "Positive reinforcement",
                "Behaviour assessment"
            ),
            imageRes = R.drawable.img_animal_behaviour
        ),
        Course(
            id = "pet-business-management",
            name = "Pet Business Management",
            fee = 1500,
            duration = "6-month programme",
            includes = listOf(
                "Starting a pet business",
                "Budgeting and pricing",
                "Marketing and clients",
                "Legal and safety basics"
            ),
            imageRes = R.drawable.img_pet_business_management
        ),
        Course(
            id = "puppy-care",
            name = "Puppy Care",
            fee = 750,
            duration = "6-week course",
            includes = listOf(
                "Feeding and routine",
                "Toilet training",
                "Early socialisation",
                "Vaccination basics"
            ),
            imageRes = R.drawable.img_puppy_care
        ),
        Course(
            id = "pet-first-aid",
            name = "Pet First Aid",
            fee = 750,
            duration = "6-week course",
            includes = listOf(
                "Checking vital signs",
                "Wounds and bleeding",
                "Choking and CPR",
                "Emergency kit"
            ),
            imageRes = R.drawable.img_pet_first_aid
        ),
        Course(
            id = "basic-dog-walking",
            name = "Basic Dog Walking",
            fee = 750,
            duration = "6-week course",
            includes = listOf(
                "Handling leads",
                "Walking safely",
                "Reading dog signals",
                "Group walking basics"
            ),
            imageRes = R.drawable.img_basic_dog_walking
        )
    )

    fun byId(id: String): Course? = courses.find { it.id == id }

    /** Discount tiers per the brief: 2 -> 5%, 3 -> 10%, 4+ -> 15%. */
    fun discountRate(numCourses: Int): Double = when {
        numCourses >= 4 -> 0.15
        numCourses == 3 -> 0.10
        numCourses == 2 -> 0.05
        else -> 0.0
    }
}
