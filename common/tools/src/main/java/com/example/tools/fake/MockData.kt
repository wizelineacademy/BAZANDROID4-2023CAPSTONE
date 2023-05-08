package com.example.tools.fake

import com.google.gson.Gson

object MockData {

    val gson = Gson()

    fun transform(java: Class<Any>) {

    }

    fun <T : Any>String.toModelResponse(java: Class<T>): T {
        return gson.fromJson(this, java)
    }

    val moviesResponse = """
        {
        "dates": {
        "maximum": "2023-05-06",
        "minimum": "2023-03-19"
        },
        "page": 1,
        "results": [
        {
        "adult": false,
        "backdrop_path": "/hiHGRbyTcbZoLsYYkO4QiCLYe34.jpg",
        "genre_ids": [
        27,
        9648,
        53
        ],
        "id": 758323,
        "original_language": "en",
        "original_title": "The Pope's Exorcist",
        "overview": "Father Gabriele Amorth, Chief Exorcist of the Vatican, investigates a young boy's terrifying possession and ends up uncovering a centuries-old conspiracy the Vatican has desperately tried to keep hidden.",
        "popularity": 6523.322,
        "poster_path": "/gNPqcv1tAifbN7PRNgqpzY8sEJZ.jpg",
        "release_date": "2023-04-05",
        "title": "The Pope's Exorcist",
        "video": false,
        "vote_average": 7.4,
        "vote_count": 554
        },
        {
        "adult": false,
        "backdrop_path": "/iJQIbOPm81fPEGKt5BPuZmfnA54.jpg",
        "genre_ids": [
        16,
        12,
        10751,
        14,
        35
        ],
        "id": 502356,
        "original_language": "en",
        "original_title": "The Super Mario Bros. Movie",
        "overview": "While working underground to fix a water main, Brooklyn plumbers—and brothers—Mario and Luigi are transported down a mysterious pipe and wander into a magical new world. But when the brothers are separated, Mario embarks on an epic quest to find Luigi.",
        "popularity": 5390.857,
        "poster_path": "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
        "release_date": "2023-04-05",
        "title": "The Super Mario Bros. Movie",
        "video": false,
        "vote_average": 7.5,
        "vote_count": 1840
        },
        {
        "adult": false,
        "backdrop_path": "/nDxJJyA5giRhXx96q1sWbOUjMBI.jpg",
        "genre_ids": [
        28,
        35,
        14,
        12
        ],
        "id": 594767,
        "original_language": "en",
        "original_title": "Shazam! Fury of the Gods",
        "overview": "Billy Batson and his foster siblings, who transform into superheroes by saying \"Shazam!\", are forced to get back into action and fight the Daughters of Atlas, who they must stop from using a weapon that could destroy the world.",
        "popularity": 3476.943,
        "poster_path": "/2VK4d3mqqTc7LVZLnLPeRiPaJ71.jpg",
        "release_date": "2023-03-15",
        "title": "Shazam! Fury of the Gods",
        "video": false,
        "vote_average": 6.8,
        "vote_count": 1499
        },
        {
        "adult": false,
        "backdrop_path": "/rbuahTzRqThIc9JOG60fQzPXJtH.jpg",
        "genre_ids": [
        878,
        12,
        28
        ],
        "id": 447365,
        "original_language": "en",
        "original_title": "Guardians of the Galaxy Volume 3",
        "overview": "Peter Quill, still reeling from the loss of Gamora, must rally his team around him to defend the universe along with protecting one of their own. A mission that, if not completed successfully, could quite possibly lead to the end of the Guardians as we know them.",
        "popularity": 2740.512,
        "poster_path": "/r2J02Z2OpNTctfOSN1Ydgii51I3.jpg",
        "release_date": "2023-05-03",
        "title": "Guardians of the Galaxy Volume 3",
        "video": false,
        "vote_average": 8.3,
        "vote_count": 568
        },
        {
        "adult": false,
        "backdrop_path": "/44immBwzhDVyjn87b3x3l9mlhAD.jpg",
        "genre_ids": [
        27,
        9648,
        53
        ],
        "id": 934433,
        "original_language": "en",
        "original_title": "Scream VI",
        "overview": "Following the latest Ghostface killings, the four survivors leave Woodsboro behind and start a fresh chapter.",
        "popularity": 2472.802,
        "poster_path": "/wDWwtvkRRlgTiUr6TyLSMX8FCuZ.jpg",
        "release_date": "2023-03-08",
        "title": "Scream VI",
        "video": false,
        "vote_average": 7.4,
        "vote_count": 1003
        },
        {
        "adult": false,
        "backdrop_path": "/cWDWUkIR22FSlxokhaNrT6jqX3n.jpg",
        "genre_ids": [
        12,
        14,
        35
        ],
        "id": 493529,
        "original_language": "en",
        "original_title": "Dungeons & Dragons: Honor Among Thieves",
        "overview": "A charming thief and a band of unlikely adventurers undertake an epic heist to retrieve a lost relic, but things go dangerously awry when they run afoul of the wrong people.",
        "popularity": 2087.59,
        "poster_path": "/v7UF7ypAqjsFZFdjksjQ7IUpXdn.jpg",
        "release_date": "2023-03-23",
        "title": "Dungeons & Dragons: Honor Among Thieves",
        "video": false,
        "vote_average": 7.5,
        "vote_count": 912
        },
        {
        "adult": false,
        "backdrop_path": "/h8gHn0OzBoaefsYseUByqsmEDMY.jpg",
        "genre_ids": [
        28,
        53,
        80
        ],
        "id": 603692,
        "original_language": "en",
        "original_title": "John Wick: Chapter 4",
        "overview": "With the price on his head ever increasing, John Wick uncovers a path to defeating The High Table. But before he can earn his freedom, Wick must face off against a new enemy with powerful alliances across the globe and forces that turn old friends into foes.",
        "popularity": 1479.162,
        "poster_path": "/vZloFAK7NmvMGKE7VkF5UHaz0I.jpg",
        "release_date": "2023-03-22",
        "title": "John Wick: Chapter 4",
        "video": false,
        "vote_average": 7.9,
        "vote_count": 1331
        },
        {
        "adult": false,
        "backdrop_path": "/7bWxAsNPv9CXHOhZbJVlj2KxgfP.jpg",
        "genre_ids": [
        27
        ],
        "id": 713704,
        "original_language": "en",
        "original_title": "Evil Dead Rise",
        "overview": "Two sisters find an ancient vinyl that gives birth to bloodthirsty demons that run amok in a Los Angeles apartment building and thrusts them into a primal battle for survival as they face the most nightmarish version of family imaginable.",
        "popularity": 1378.602,
        "poster_path": "/5ik4ATKmNtmJU6AYD0bLm56BCVM.jpg",
        "release_date": "2023-04-12",
        "title": "Evil Dead Rise",
        "video": false,
        "vote_average": 7,
        "vote_count": 381
        },
        {
        "adult": false,
        "backdrop_path": "/a2tys4sD7xzVaogPntGsT1ypVoT.jpg",
        "genre_ids": [
        53,
        35,
        80
        ],
        "id": 804150,
        "original_language": "en",
        "original_title": "Cocaine Bear",
        "overview": "Inspired by a true story, an oddball group of cops, criminals, tourists and teens converge in a Georgia forest where a 500-pound black bear goes on a murderous rampage after unintentionally ingesting cocaine.",
        "popularity": 1031.723,
        "poster_path": "/gOnmaxHo0412UVr1QM5Nekv1xPi.jpg",
        "release_date": "2023-02-22",
        "title": "Cocaine Bear",
        "video": false,
        "vote_average": 6.4,
        "vote_count": 969
        },
        {
        "adult": false,
        "backdrop_path": "/eEF40Xk2twM3WjRNZftfo771gjv.jpg",
        "genre_ids": [
        878,
        12,
        53
        ],
        "id": 700391,
        "original_language": "en",
        "original_title": "65",
        "overview": "65 million years ago, the only 2 survivors of a spaceship from Somaris that crash-landed on Earth must fend off dinosaurs and reach the escape vessel in time before an imminent asteroid strike threatens to destroy the planet.",
        "popularity": 944.383,
        "poster_path": "/rzRb63TldOKdKydCvWJM8B6EkPM.jpg",
        "release_date": "2023-03-02",
        "title": "65",
        "video": false,
        "vote_average": 6.3,
        "vote_count": 937
        },
        {
        "adult": false,
        "backdrop_path": "/cZzZlwGvxiByXam0lZ57J9IN233.jpg",
        "genre_ids": [
        27
        ],
        "id": 1008005,
        "original_language": "es",
        "original_title": "La niña de la comunión",
        "overview": "Spain, late 1980s. Newcomer Sara tries to fit in with the other teens in this tight-knit small town in the province of Tarragona. If only she were more like her extroverted best friend, Rebe. They go out one night at a nightclub, on the way home, they come upon a little girl holding a doll, dressed for her first communion. And that's when the nightmare begins.",
        "popularity": 947.947,
        "poster_path": "/uYxrWr9o44yO0HvVfFFHGu01gfX.jpg",
        "release_date": "2023-02-10",
        "title": "The Communion Girl",
        "video": false,
        "vote_average": 6.2,
        "vote_count": 83
        },
        {
        "adult": false,
        "backdrop_path": "/c3hl9E8E7b9opXDFVF5tSyk0ykr.jpg",
        "genre_ids": [
        16,
        35,
        10751,
        12,
        14
        ],
        "id": 816904,
        "original_language": "es",
        "original_title": "Momias",
        "overview": "Through a series of unfortunate events, three mummies end up in present-day London and embark on a wacky and hilarious journey in search of an old ring belonging to the Royal Family, stolen by ambitious archaeologist Lord Carnaby.",
        "popularity": 769.045,
        "poster_path": "/qVdrYN8qu7xUtsdEFeGiIVIaYd.jpg",
        "release_date": "2023-01-05",
        "title": "Mummies",
        "video": false,
        "vote_average": 7.2,
        "vote_count": 256
        },
        {
        "adult": false,
        "backdrop_path": "/bKWFetR4injOJXOGHeLuTD7wHI4.jpg",
        "genre_ids": [
        27,
        53,
        14
        ],
        "id": 994128,
        "original_language": "es",
        "original_title": "Viejos",
        "overview": "An octogenarian named Manuel falls into a state of dementia after the sudden suicide of his wife, sparking a series of paranormal events that will put his family’s lives at risk.",
        "popularity": 736.281,
        "poster_path": "/88f35mGJOIE1nXgst5o4FvEDVzr.jpg",
        "release_date": "2023-04-21",
        "title": "The Elderly",
        "video": false,
        "vote_average": 5.8,
        "vote_count": 6
        },
        {
        "adult": false,
        "backdrop_path": "/wD2kUCX1Bb6oeIb2uz7kbdfLP6k.jpg",
        "genre_ids": [
        27,
        53
        ],
        "id": 980078,
        "original_language": "en",
        "original_title": "Winnie the Pooh: Blood and Honey",
        "overview": "Christopher Robin is headed off to college and he has abandoned his old friends, Pooh and Piglet, which then leads to the duo embracing their inner monsters.",
        "popularity": 720.543,
        "poster_path": "/ewF3IlGscc7FjgGEPcQvZsAsgAW.jpg",
        "release_date": "2023-01-27",
        "title": "Winnie the Pooh: Blood and Honey",
        "video": false,
        "vote_average": 5.7,
        "vote_count": 554
        },
        {
        "adult": false,
        "backdrop_path": "/m1fgGSLK0WvRpzM1AmZu38m0Tx8.jpg",
        "genre_ids": [
        28
        ],
        "id": 842945,
        "original_language": "en",
        "original_title": "Supercell",
        "overview": "Good-hearted teenager William always lived in hope of following in his late father’s footsteps and becoming a storm chaser. His father’s legacy has now been turned into a storm-chasing tourist business, managed by the greedy and reckless Zane Rogers, who is now using William as the main attraction to lead a group of unsuspecting adventurers deep into the eye of the most dangerous supercell ever seen.",
        "popularity": 660.954,
        "poster_path": "/gbGHezV6yrhua0KfAgwrknSOiIY.jpg",
        "release_date": "2023-03-17",
        "title": "Supercell",
        "video": false,
        "vote_average": 6.3,
        "vote_count": 142
        },
        {
        "adult": false,
        "backdrop_path": "/exI61quYa7xMfcIDSp674UnvrhG.jpg",
        "genre_ids": [
        10752,
        28
        ],
        "id": 840326,
        "original_language": "fi",
        "original_title": "Sisu",
        "overview": "Deep in the wilderness of Lapland, Aatami Korpi is searching for gold but after he stumbles upon Nazi patrol, a breathtaking and gold-hungry chase through the destroyed and mined Lapland wilderness begins.",
        "popularity": 583.847,
        "poster_path": "/dHx5yuBb05U9vNaNhIBD7jWyxPk.jpg",
        "release_date": "2023-01-27",
        "title": "Sisu",
        "video": false,
        "vote_average": 7.1,
        "vote_count": 36
        },
        {
        "adult": false,
        "backdrop_path": "/nJ4i3aFmrRqXvnDPBvhk9fKam5I.jpg",
        "genre_ids": [
        14,
        28,
        12
        ],
        "id": 455476,
        "original_language": "en",
        "original_title": "Knights of the Zodiac",
        "overview": "When a headstrong street orphan, Seiya, in search of his abducted sister unwittingly taps into hidden powers, he discovers he might be the only person alive who can protect a reincarnated goddess, sent to watch over humanity. Can he let his past go and embrace his destiny to become a Knight of the Zodiac?",
        "popularity": 520.537,
        "poster_path": "/tBiUXvCqz34GDeuY7jK14QQdtat.jpg",
        "release_date": "2023-04-27",
        "title": "Knights of the Zodiac",
        "video": false,
        "vote_average": 6.4,
        "vote_count": 27
        },
        {
        "adult": false,
        "backdrop_path": "/aHcWyh0n4YbBy9gxYCDlgsNVS3M.jpg",
        "genre_ids": [
        27
        ],
        "id": 631842,
        "original_language": "en",
        "original_title": "Knock at the Cabin",
        "overview": "While vacationing at a remote cabin, a young girl and her two fathers are taken hostage by four armed strangers who demand that the family make an unthinkable choice to avert the apocalypse. With limited access to the outside world, the family must decide what they believe before all is lost.",
        "popularity": 507.187,
        "poster_path": "/dm06L9pxDOL9jNSK4Cb6y139rrG.jpg",
        "release_date": "2023-02-01",
        "title": "Knock at the Cabin",
        "video": false,
        "vote_average": 6.4,
        "vote_count": 1397
        },
        {
        "adult": false,
        "backdrop_path": "/70aVSo3fuZ94jyQ3rT64afEf8lV.jpg",
        "genre_ids": [
        16,
        12,
        35,
        10751,
        14
        ],
        "id": 676710,
        "original_language": "en",
        "original_title": "The Amazing Maurice",
        "overview": "Maurice is a streetwise ginger cat who comes up with a money-making scam by befriending a group of self-taught talking rats. When Maurice and the rodents meet a bookworm called Malicia, their little con soon goes down the drain.",
        "popularity": 448.912,
        "poster_path": "/ydhZeUjbzVEFclUpMhLfDZSavUY.jpg",
        "release_date": "2022-12-16",
        "title": "The Amazing Maurice",
        "video": false,
        "vote_average": 7,
        "vote_count": 73
        },
        {
        "adult": false,
        "backdrop_path": "/uNK5dHo6HJpwk1BPcDHHyvX7ZLz.jpg",
        "genre_ids": [
        28,
        53
        ],
        "id": 983768,
        "original_language": "en",
        "original_title": "Black Warrant",
        "overview": "A semi-retired special ops assassin and a DEA agent cross paths on separate missions to stop a cyber terrorist organization that has built a dangerous machine threatening to attack the power grid and bring catastrophe to the world.",
        "popularity": 461.742,
        "poster_path": "/s95yjWcHbRpS32exC8AeA2LNWXS.jpg",
        "release_date": "2023-01-09",
        "title": "Black Warrant",
        "video": false,
        "vote_average": 5.3,
        "vote_count": 23
        }
        ],
        "total_pages": 92,
        "total_results": 1827
        }
    """.trimIndent()

    val genresResponse = """
        {
          "genres": [
            {
              "id": 28,
              "name": "Action"
            },
            {
              "id": 12,
              "name": "Adventure"
            },
            {
              "id": 16,
              "name": "Animation"
            },
            {
              "id": 35,
              "name": "Comedy"
            },
            {
              "id": 80,
              "name": "Crime"
            },
            {
              "id": 99,
              "name": "Documentary"
            },
            {
              "id": 18,
              "name": "Drama"
            },
            {
              "id": 10751,
              "name": "Family"
            },
            {
              "id": 14,
              "name": "Fantasy"
            },
            {
              "id": 36,
              "name": "History"
            },
            {
              "id": 27,
              "name": "Horror"
            },
            {
              "id": 10402,
              "name": "Music"
            },
            {
              "id": 9648,
              "name": "Mystery"
            },
            {
              "id": 10749,
              "name": "Romance"
            },
            {
              "id": 878,
              "name": "Science Fiction"
            },
            {
              "id": 10770,
              "name": "TV Movie"
            },
            {
              "id": 53,
              "name": "Thriller"
            },
            {
              "id": 10752,
              "name": "War"
            },
            {
              "id": 37,
              "name": "Western"
            }
          ]
        }
    """.trimIndent()

    val latestResponse = """
        {
          "adult": false,
          "backdrop_path": "/vhV8e5Ak9BMX4wCmZe9DK1L3XsM.jpg",
          "belongs_to_collection": null,
          "budget": 0,
          "genres": [],
          "homepage": "",
          "id": 1124041,
          "imdb_id": "tt14123058",
          "original_language": "en",
          "original_title": "Queen of Hearts",
          "overview": "Fed up with unfaithful and abusive men, a woman begins targeting them for death. A female cop chasing the killer falls prey to an abusive man herself.",
          "popularity": 0,
          "poster_path": null,
          "production_companies": [],
          "production_countries": [],
          "release_date": "",
          "revenue": 0,
          "runtime": 78,
          "spoken_languages": [],
          "status": "Released",
          "tagline": "",
          "title": "Queen of Hearts",
          "video": false,
          "vote_average": 0,
          "vote_count": 0
        }
    """.trimIndent()
}