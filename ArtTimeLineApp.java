import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ArtTimeLineApp extends Application {
	
    private double timelineWidth;
    private double timelineHeight;
    
    private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        timelineWidth = screenBounds.getWidth(); 
        timelineHeight = screenBounds.getHeight();		
        this.primaryStage = primaryStage;    
		primaryStage.setTitle("Digital Art History Timeline");
		primaryStage.setMaximized(true);
		Scene mainMenuScene = createMainMenuScene(primaryStage);
		primaryStage.setScene(mainMenuScene);
		primaryStage.show();
	}

	private Scene createMainMenuScene(Stage primaryStage) {
	    VBox layout = new VBox(20);
	    layout.setAlignment(Pos.CENTER);
	    layout.setPadding(new Insets(400, 0, 0, 0)); 

	    // Load background image for the main menu
	    Image image = new Image("file:/Users/saleha/Desktop/fbg.png");
	    ImageView imageView = new ImageView(image);
	    imageView.fitWidthProperty().bind(primaryStage.widthProperty());
	    imageView.fitHeightProperty().bind(primaryStage.heightProperty());
	    imageView.setPreserveRatio(false);

	    Label introText = new Label("Welcome to the Digital Art History Timeline! \nExplore key events in the history of digital art.");
	    introText.setFont(Font.font("Georgia", FontWeight.BOLD, 30));
	    introText.setTextFill(Color.BLACK);

	    Button viewTimelineButton = new Button("View Timeline");
	    viewTimelineButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
	    viewTimelineButton.setOnAction(e -> primaryStage.setScene(createTimelineScene(primaryStage)));

	    Button instructionsButton = new Button("Instructions");
	    instructionsButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
	    instructionsButton.setOnAction(e -> showInstructions());

	    // Adding all elements to the layout
	    layout.getChildren().addAll(introText, viewTimelineButton, instructionsButton);

	    // Creating a StackPane rootPane and adding layout and imageView
	    StackPane rootPane = new StackPane();
	    rootPane.getChildren().addAll(imageView, layout); 

	    primaryStage.setFullScreen(true);
	    return new Scene(rootPane);
	}


	private Scene createTimelineScene(Stage primaryStage) {
		ScrollPane scrollPane = new ScrollPane();
		AnchorPane contentPane = new AnchorPane();

		// Background image for the timeline
		Image timelineBackground = new Image("file:/Users/saleha/Desktop/background.png");
		ImageView imageView = new ImageView(timelineBackground);
		imageView.setPreserveRatio(false);
		imageView.fitWidthProperty().bind(contentPane.widthProperty());
		imageView.fitHeightProperty().bind(contentPane.heightProperty());
		contentPane.getChildren().add(imageView);

		setupTimeline(contentPane, primaryStage);
		scrollPane.setContent(contentPane);

		Button backButton = new Button("Back");
		backButton.setOnAction(e -> primaryStage.setScene(createMainMenuScene(primaryStage)));
		AnchorPane.setTopAnchor(backButton, 10.0);
		AnchorPane.setRightAnchor(backButton, 10.0);
		contentPane.getChildren().add(backButton);

		// Instructions Button
	    Button instructionsButton = new Button("Instructions");
	    instructionsButton.setOnAction(e -> showTimelineInstructions());
	    AnchorPane.setTopAnchor(instructionsButton, 10.0);
	    AnchorPane.setLeftAnchor(instructionsButton, 10.0);
	    contentPane.getChildren().add(instructionsButton);

	    // Bind the size of the scroll pane to the stage
	    scrollPane.prefWidthProperty().bind(primaryStage.widthProperty());
	    scrollPane.prefHeightProperty().bind(primaryStage.heightProperty());
	    contentPane.prefWidthProperty().bind(scrollPane.widthProperty());
	    contentPane.prefHeightProperty().bind(scrollPane.heightProperty());

	    primaryStage.setFullScreen(true);
	    return new Scene(scrollPane, timelineWidth, timelineHeight);
	}


	private void setupTimeline(AnchorPane anchorPane, Stage primaryStage) {
		// Create a list of ArtEvent objects
		List<ArtEvent> events = Arrays.asList(
				new ArtEvent(
						"The Start of Digital Art",  // Title
						"1960",  // Date
						"file:/Users/saleha/Desktop/1960.png",  // Image path for the key artwork
						200, 710,  // X and Y coordinates for the event node
						"The 1960s marked the start of a new era in the visual arts, characterized by the introduction of digital tools in artistic creation. John Whitney, a pioneer in this field, leveraged the potential of computers to manipulate and transform visual materials. His explorations laid the groundwork for the evolution of digital art, influencing subsequent generations of artists and technologists.\n\n"
						+ "Whitney's experiments with motion and light through computational means were revolutionary. He developed his own motion graphics techniques, which allowed him to create complex, mesmerizing visual effects that were previously unimaginable. His work not only expanded the boundaries of artistic expression but also demonstrated the vast possibilities of computer graphics.\n\n"
						+ "This seminal period in art history was not just about new aesthetics but also about new ways of thinking about and seeing the world through the lens of technology. Whitney's contributions were instrumental in shaping the discourse around digital media and its role in art.",
						"John Whitney",  // Artist name
						"This image captures John Whitney's innovative approach to digital art,                                                                           "
						+ "\n where he applied mathematical formulas to manipulate and animate "
						+ "\n images, pioneering motion graphics techniques. His work revolutionized "
						+ "\n the use of computers in artistic creation, offering a new visual language."

						),

				new ArtEvent(
						"First Digital Art Creation",  // Title
						"1965",  // Date
						"file:/Users/saleha/Desktop/1965.png",  // Image path for the key artwork
						400, 710,  // X and Y coordinates for the event node
						"In 1965, a groundbreaking moment in the history of digital art occurred when Frieder Nake, an artist and mathematician, created one of the first digital artworks. While studying in Stuttgart, Germany, Nake input an algorithm into a room-sized ER 56 computer, which mathematically interpreted a 1929 painting by Paul Klee. The computer controlled a flatbed drawing machine that produced several images, and London's Victoria and Albert Museum hailed Nake's work as the 'most complex algorithmic work of its day.'\n\n"
						+ "Nake's pioneering creation demonstrated the incredible potential of computers in art, bridging the gap between mathematics and aesthetics. His work not only reflected the innovative spirit of the era but also laid the foundation for future explorations in digital art, influencing generations of artists to come.\n\n"
						+ "The selected image showcases the successful outcome of Nake's algorithmic interpretation, highlighting the unique interplay between technology and creativity.",
						"Frieder Nake",  // Artist name
						"This image represents Frieder Nake's innovative approach to digital art, where he used                                                  "
						+ "\n a computer to mathematically interpret a painting by Paul Klee. His groundbreaking work "
						+ "\n in the field of algorithmic art set the stage for future digital artists and demonstrated "
						+ "\n the creative possibilities of merging art with technology."
						),

				new ArtEvent(
						"Experiments in Art and Technology Formed",  // Title
						"1967",  // Date
						"file:/Users/saleha/Desktop/1967.png",  // Image path for the key artwork
						600, 710,  // X and Y coordinates for the event node
						"In 1967, the art and technology worlds converged in a groundbreaking collaboration known as Experiments in Art and Technology (EAT). The group was formed by engineers Billy Klüver, Fred Waldhauer, and artists Robert Rauschenberg and Robert Whitman, aiming to bridge the gap between art and emerging technologies. EAT's mission was to promote and facilitate collaboration between artists and engineers, resulting in a series of innovative installations and performances that incorporated electronic systems.\n\n"
						+ "While these early experiments were not strictly 'digital' due to the just beginning state of technology, they were pioneering in exploring new artistic mediums and approaches. The artworks produced under EAT's umbrella laid the groundwork for a new type of art that blended technical innovation with creative expression. The legacy of EAT is seen in its influence on subsequent generations of digital artists and technologists, fostering a spirit of interdisciplinary collaboration that continues to shape the digital art landscape.\n\n"
						+ "This event highlighted the potential for art and technology to interact in novel ways, showcasing how electronics and innovative systems could be used to create immersive and interactive artistic experiences.",
						"Billy Klüver, Fred Waldhauer, Robert Rauschenberg, Robert Whitman ",  // Artists' names
						"This image represents the collaborative spirit of EAT, where artists and engineers came together                               "
						+ "\n to explore the intersection of art and technology. Their innovative installations and performances "
						+ "\n paved the way for future explorations in digital and electronic art, setting a precedent for "
						+ "\n interdisciplinary collaboration in creative fields."
						),


				new ArtEvent(
						"Exploration of Technology",  // Title
						"1969",  // Date
						"file:/Users/saleha/Desktop/1969.png",  // Image path for the key artwork
						800, 710,  // X and Y coordinates for the event node
						"In 1969, conceptual artist Allan Kaprow created \"Hello,\" an interactive video happening that "
						+ "was part of \"The Medium Is the Medium,\" an experimental television program. The project "
						+ "connected four remote locations via a closed-circuit television network, allowing groups of "
						+ "people to interact through T.V. monitors. Kaprow functioned as the 'director' in the studio "
						+ "control room, orchestrating the communication between participants.\n\n"
						+ "Kaprow's interest lay in exploring communications media as non-communications, highlighting "
						+ "how technology can mediate and disrupt human interaction. \"Hello\" provided a critique of how "
						+ "technology influences our connections with others, metaphorically short-circuiting the television "
						+ "network to emphasize the importance of human connections.",
						"Allan Kaprow",  // Artist name
						"This image represents Allan Kaprow's \"Hello,\" an interactive video happening that critiqued                           "
						+ "\n how technology mediates and disrupts human interaction. Kaprow used a closed-circuit television "
						+ "\n network to connect people across different locations, emphasizing the importance of connecting "
						+ "\n with others."
						),

				new ArtEvent(
						"Lillian Schwartz's 'Pixillation'",  // Title
						"1970",  // Date
						"file:/Users/saleha/Desktop/1970.png",  // Image path for the key artwork
						1000, 710,  // X and Y coordinates for the event node
						"In 1970, Lillian Schwartz, a pioneer in computer-generated art, created 'Pixillation,' a 16 mm abstract film exploring the potential of computer technology. "
						+ "Schwartz, who had worked as a nurse in postwar Japan before turning to kinetic art in the 1960s, joined Bell Laboratories as an artist-in-residence. "
						+ "She was a key figure in the New York art scene, collaborating with the Experiments in Art and Technology group.\n\n"
						+ "Schwartz's film 'Pixillation' used early computer graphics to generate mesmerizing abstract shapes and patterns. "
						+ "The film showcased the exciting possibilities of a new visual paradigm and reflected Schwartz's unique position as both an artist and a scientist. "
						+ "Her work laid the foundation for future explorations in digital art, emphasizing the relationship between art and technology.\n\n"
						+ "Schwartz's contribution to the field is significant, as her pioneering work at Bell Laboratories demonstrated the creative potential of computer-generated imagery.",
						"Lillian Schwartz",  // Artist name
						"This image represents Lillian Schwartz's 'Pixillation,' an early example of computer-generated art.                                   "
						+ "\n Schwartz's innovative use of technology paved the way for future explorations in digital art.                                     "
						+ "\n Her work emphasizes the relationship between art and technology, showcasing the exciting                   "
						+ "\n potential of computer-generated imagery."
						),

				new ArtEvent(
						"Harold Cohen and AARON",  // Title
						"1972",  // Date
						"file:/Users/saleha/Desktop/aaron.png",  // Image path for the key artwork
						1200, 710,  // X and Y coordinates for the event node
						"In 1972, Harold Cohen, an established painter, began developing AARON, an AI program that created original artistic images. "
						+ "The program evolved over several decades, exploring both abstract and representational imagery, and even adding color. "
						+ "AARON produced unique artworks through a series of custom-built machines, including digital painting machines and large-scale inkjet printers.\n\n"
						+ "Cohen's collaboration with AARON highlighted the potential of artificial intelligence in translating an artist's knowledge and process into code. "
						+ "His work emphasized the interplay between technology and art, posing questions about creativity, authorship, and collaboration in the context of AI. "
						+ "AARON's output was exhibited worldwide, including at the Whitney Museum of American Art, and became an artistic equivalent of the Turing test.\n\n"
						+ "Cohen's innovative approach to art and technology paved the way for future explorations in AI-generated art, leaving a lasting impact on the field.",
						"Harold Cohen",  // Artist name
						"This image represents Harold Cohen's AARON, an AI program that created unique artistic images.                            "
						+ "\n The program evolved over decades, exploring both abstract and representational imagery.                                              "
						+ "\n Cohen's work highlighted the potential of artificial intelligence in art, raising questions about              "
						+ "\n about creativity, authorship and collaboration.                                 "
						), 

				new ArtEvent(
						"Andy Warhol and Digital Art",  // Title
						"1985",  // Date
						"file:/Users/saleha/Desktop/andywarhol.png",  // Image path for the key artwork
						1400, 710,  // X and Y coordinates for the event node
						"In 1985, Andy Warhol, a leading figure in the Pop Art movement, began experimenting with digital art using the Commodore Amiga computer. "
						+ "He created a series of digital artworks, including reinterpretations of his famous works and new pieces like 'Venus'. "
						+ "Warhol's experimentation with digital media marked one of the earliest forays into computer-generated art by a major artist.\n\n"
						+ "Warhol's digital art highlighted the intersection of technology and creativity, "
						+ "challenging traditional notions of art production. His work with the Amiga paved the way for future artists to explore the creative potential of digital tools.\n\n"
						+ "Warhol's innovative approach to art and technology demonstrated the versatility of the digital medium, "
						+ "setting the stage for the evolution of digital art in the decades to follow.",
						"Andy Warhol",  // Artist name
						"This image represents Andy Warhol's early exploration of digital art using the Commodore Amiga.                               "
						+ "\n He created digital reinterpretations of his famous works, including 'Venus', showcasing the                              "
						+ "\n intersection of technology and creativity. Warhol's digital art challenged traditional notions  "
						+ "\n of art production and paved the way for future artists."
						),

				new ArtEvent(
						"Olia Lialina and 'My Boyfriend Came Back From the War'",  // Title
						"1996",  // Date
						"file:/Users/saleha/Desktop/olia_lialina.png",  // Image path for the key artwork
						1600, 710,  // X and Y coordinates for the event node
						"In 1996, Russian internet artist Olia Lialina created 'My Boyfriend Came Back From the War,' an interactive hypertext narrative. "
						+ "The browser-based artwork, which consisted of black and white GIF images and HTML frames, tells a nonlinear story of a couple reuniting after a military conflict.\n\n"
						+ "The piece is considered a pioneering work of internet art, reflecting the nascent digital culture of the 1990s. "
						+ "The narrative unfolds through a series of clickable frames, reminiscent of early silent movies, creating a mosaic of emotions and fragmented experiences. "
						+ "Lialina's innovative approach showcased the potential of the internet for storytelling, blending cinematic elements with digital interactivity.\n\n"
						+ "The work has since been remixed and adapted by various artists, cementing its status as a classic in net art. "
						+ "Lialina's contribution to the field demonstrated the creative potential of digital technology and laid the groundwork for future internet-based artworks.",
						"Olia Lialina",  // Artist name
						"This image represents Olia Lialina's 'My Boyfriend Came Back From the War,' an influential                                                 "
						+ "\n internet artwork. The piece, a pioneering example of interactive hypertext storytelling,                                               "
						+ "\n tells the story of a couple reuniting after a military conflict. Lialina's innovative "
						+ "\n use of black and white GIF images and HTML frames showcased the potential of digital "
						+ "\n technology for narrative art."
						),

				new ArtEvent(
						"Casey Reas and 'Software Structures'",  // Title
						"2004",  // Date
						"file:/Users/saleha/Desktop/software_structures.png",  // Image path for the key artwork
						1800, 710,  // X and Y coordinates for the event node
						"In 2004, American artist Casey Reas, known for his conceptual and procedural artworks, created 'Software Structures,' a unique piece that visualizes software algorithms as dynamic, generative drawings. "
						+ "The artwork, inspired by minimalist and conceptual art, explores the interplay between human intention and computational execution.\n\n"
						+ "Reas, a co-creator of the Processing programming language, used code to create a series of instructions that produce evolving, abstract forms on the screen. "
						+ "'Software Structures' demonstrates the potential of programming as an artistic medium, allowing for the continuous transformation of visual elements.\n\n"
						+ "The piece is a testament to Reas' pioneering work in software art, where the artist's role becomes that of a conductor, orchestrating a visual symphony through code. "
						+ "The evolving, ever-changing nature of the artwork invites viewers to contemplate the relationship between technology, art, and human creativity.",
						"Casey Reas",  // Artist name
						"This image represents Casey Reas' 'Software Structures,' a work that visualizes software                                                               "
						+ "\n algorithms as dynamic, generative drawings. The artwork explores the interplay between                                                             "
						+ "\n human intention and computational execution, highlighting the potential of programming "
						+ "\n as an artistic medium. Reas' pioneering work in software art invites viewers to "
						+ "\n contemplate the relationship between technology, art, and human creativity."
						),

				new ArtEvent(
						"Golan Levin and 'Manual Input Sessions'",  // Title
						"2004",  // Date
						"file:/Users/saleha/Desktop/manual_input_sessions.png",  // Image path for the key artwork
						2000, 710,  // X and Y coordinates for the event node
						"In 2004, artist and engineer Golan Levin, along with Zachary Lieberman, created 'Manual Input Sessions,' an innovative audiovisual performance exploring the expressive possibilities of hand gestures and finger movements. "
						+ "Using a unique blend of analog and digital technology, the performance involved interactive software, overhead projectors, and computer video projectors. The analog and digital projections were aligned to create a hybridized, dynamic light display.\n\n"
						+ "During the performance, a computer vision system analyzed the performers' hand silhouettes as they moved across the overhead projectors' glass tops. The custom software generated synthetic graphics and sounds in response to the hand gestures, creating a magical augmented-reality shadow play.\n\n"
						+ "'Manual Input Sessions' highlighted the potential of human-machine interaction, emphasizing how gestures and movements can be transformed into expressive audiovisual experiences. The piece exemplified Levin's interest in responsive systems and interactive art, showcasing the intersection of technology, creativity, and performance.",
						"Golan Levin",  // Artist name
						"This image represents Golan Levin and Zachary Lieberman's 'Manual Input Sessions,' an audiovisual                                              "
						+ "\n performance that explores the expressive possibilities of hand gestures and finger movements.                                               "
						+ "\n The performance blended analog and digital projections to create a unique augmented-reality "
						+ "\n shadow play. The artwork highlights the potential of human-machine interaction, showcasing "
						+ "\n the intersection of technology, creativity, and performance."
						),


				new ArtEvent(
						"Rafael Lozano-Hemmer and 'Pulse Room'",  // Title
						"2006",  // Date
						"file:/Users/saleha/Desktop/pulse_room.png",  // Image path for the key artwork
						2200, 710,  // X and Y coordinates for the event node
						"In 2006, Mexican-Canadian electronic artist Rafael Lozano-Hemmer created 'Pulse Room,' an interactive installation featuring over 300 incandescent light bulbs. "
						+ "The installation captures the heartbeat of participants and translates it into light, with each bulb pulsating in sync with a heartbeat recorded by a sensor.\n\n"
						+ "The piece, which has been exhibited internationally, embodies the artist's interest in creating immersive experiences that blend technology and human interaction. "
						+ "The installation creates a mesmerizing effect as the bulbs flicker with the rhythms of human life, inviting viewers to contemplate the interconnectedness of technology and biology.\n\n"
						+ "Lozano-Hemmer's work explores themes of perception, memory, and surveillance, often using innovative technology to engage audiences in thought-provoking ways. "
						+ "'Pulse Room' exemplifies his unique approach to art, where the participation of the audience is integral to the artwork's impact.",
						"Rafael Lozano-Hemmer",  // Artist name
						"This image represents Rafael Lozano-Hemmer's 'Pulse Room,' an interactive installation with over                                         "
						+ "\n 300 light bulbs. The piece captures the heartbeat of participants and translates it into                                            "
						+ "\n light, creating a mesmerizing effect. Lozano-Hemmer's work explores themes of perception "
						+ "\n and memory, blending technology with human interaction."
						),

				new ArtEvent(
						"Petra Cortright and 'NIKI LUCY LOLA VIOLA'",  // Title
						"2015",  // Date
						"file:/Users/saleha/Desktop/nikki_lucy-voila.png",  // Image path for the key artwork
						2400, 710,  // X and Y coordinates for the event node
						"In 2015, Petra Cortright, a contemporary artist known for her digital and internet-based art, created 'NIKI LUCY LOLA VIOLA,' an innovative digital artwork combining video and painting elements. "
						+ "The piece featured virtual strippers, extracted from software and set against minimal, green-screen landscapes, creating a unique blend of digital imagery and performative art.\n\n"
						+ "Cortright’s work often explores the aesthetics of digital visual language and consumer culture. 'NIKI LUCY LOLA VIOLA' was part of an exhibition at the Depart Foundation in Los Angeles, where the artist also showcased her 'painting videos,' which used Photoshop layers and camera-motion techniques to create animated digital images.\n\n"
						+ "The piece reflects Cortright's exploration of the representation of women in digital spaces, questioning how they are viewed in a virtual landscape. Her work, blending digital and physical art, highlights her innovative approach to contemporary art and technology.",
						"Petra Cortright",  // Artist name
						"This image represents Petra Cortright's 'NIKI LUCY LOLA VIOLA,' a digital artwork featuring                                            "
						+ "\n virtual strippers against minimal, green-screen landscapes. The work explores the aesthetics "
						+ "\n of digital visual language and consumer culture, reflecting Cortright's innovative approach "
						+ "\n to contemporary art and technology."
						)
				);

		// Loop through each event and add it to the timeline
		for (ArtEvent event : events) {
			addEventNode(anchorPane, event.getNodeX(), event.getNodeY(), event, primaryStage);
		}
	}

	private void addEventNode(AnchorPane pane, double x, double y, ArtEvent event, Stage primaryStage) {
		Circle circle = new Circle(x, y, 30, Color.PINK);
		circle.setEffect(new DropShadow(18, Color.GRAY));
		circle.setOnMouseEntered(e -> circle.setFill(Color.BEIGE));
		circle.setOnMouseExited(e -> circle.setFill(Color.PINK));

		Label label = new Label(String.valueOf(event.getDate()));
		label.setLayoutX(x - 20);
		label.setLayoutY(y + 40);
		label.setTextFill(Color.BLACK);

		circle.setOnMouseClicked(e -> displayEventDetails(event, primaryStage));

		pane.getChildren().addAll(circle, label);
	}

	private void displayEventDetails(ArtEvent event, Stage primaryStage) {
		BorderPane layout = new BorderPane();
		layout.setStyle("-fx-background-color: black;");  // Black background for contrast

		// Title, Date, and Artist at the top
		VBox topBox = new VBox(10);
		Label titleLabel = new Label(event.getTitle());
		titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		titleLabel.setTextFill(Color.WHITE);
		Label dateLabel = new Label("Year: " + event.getDate().toString());
		dateLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
		dateLabel.setTextFill(Color.WHITE);
		Label artistLabel = new Label("Artist: " + event.getArtistName());
		artistLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
		artistLabel.setTextFill(Color.WHITE);
		topBox.getChildren().addAll(titleLabel, dateLabel, artistLabel);
		layout.setTop(topBox);

		// Image and key artwork description on the left or center
		VBox leftBox = new VBox(10);
		Image image = new Image(event.getImagePath());
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(250);
		imageView.setPreserveRatio(true);
		Label artworkDescriptionLabel = new Label(event.getKeyArtworkDescription());
		artworkDescriptionLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
		artworkDescriptionLabel.setWrapText(true);
		artworkDescriptionLabel.setTextFill(Color.WHITE);
		leftBox.getChildren().addAll(imageView, artworkDescriptionLabel);
		layout.setLeft(leftBox);

		VBox backgroundBox = new VBox(10);
		Label backgroundHeading = new Label("Background");
		backgroundHeading.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		backgroundHeading.setTextFill(Color.WHITE);
		Label backgroundText = new Label(event.getDetailedDescription());
		backgroundText.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
		backgroundText.setWrapText(true);
		backgroundText.setTextFill(Color.WHITE);
		backgroundBox.getChildren().addAll(backgroundHeading, backgroundText);
		backgroundBox.setVisible(true);  // Make it always visible

		layout.setCenter(backgroundBox);

		// Close button
		Button closeButton = new Button("Close");
		closeButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		closeButton.setTextFill(Color.WHITE);
		closeButton.setStyle("-fx-background-color: grey;");
		closeButton.setOnAction(e -> ((Node) e.getSource()).getScene().getWindow().hide());
		layout.setBottom(closeButton);

		// Create and configure the popup stage
		Stage popupStage = new Stage();
		popupStage.initOwner(primaryStage);
	    popupStage.setOnHidden(e -> primaryStage.requestFocus());  // Request focus when hidden
		popupStage.initModality(Modality.APPLICATION_MODAL);
		popupStage.setTitle("Event Details");
		Scene scene = new Scene(layout, 700, 500);
		popupStage.setScene(scene);
		popupStage.showAndWait();
	}

	private void showInstructions() {
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.initOwner(primaryStage); 
	    alert.initModality(Modality.APPLICATION_MODAL); 
	    alert.setTitle("Welcome to the Digital Art History Timeline");
	    alert.setHeaderText("Instructions");
	    alert.setContentText("1. Explore the timeline by clicking on the events to learn more about each significant moment in digital art history.\n "
	    		+ "\n 2. Use the back button to return to the main menu. \n"
	    		+ "\n 3. It is recommended to view in full screen mode at all times.");

	    alert.showAndWait();
	}
	
	private void showTimelineInstructions() {
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.initOwner(primaryStage); 
	    alert.initModality(Modality.APPLICATION_MODAL); 
	    alert.setTitle("Instructions");
	    alert.setHeaderText("Viewing Event Details");
	    alert.setContentText("Hit the full-screen button on the popup to view more information about each event.");
	    alert.showAndWait();
	}

	public static void main(String[] args) {
		launch(args);
	}
}